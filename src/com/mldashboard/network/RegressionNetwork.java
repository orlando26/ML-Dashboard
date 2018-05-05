package com.mldashboard.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.encog.app.analyst.AnalystFileFormat;
import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.csv.normalize.AnalystNormalizeCSV;
import org.encog.app.analyst.csv.segregate.SegregateCSV;
import org.encog.app.analyst.csv.segregate.SegregateTargetPercent;
import org.encog.app.analyst.csv.shuffle.ShuffleCSV;
import org.encog.app.analyst.wizard.AnalystWizard;
import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.EncogUtility;

import com.mldashboard.model.Feature;
import com.mldashboard.util.FilesPath;





public class RegressionNetwork {

	private FilesPath filesPath;

	public RegressionNetwork(){
		filesPath = new FilesPath();
	}

	public void shuffle(String source){
		// Shuffle the base file
		ShuffleCSV shuffle = new ShuffleCSV();
		shuffle.analyze(new File(source), true, CSVFormat.ENGLISH);
		shuffle.setProduceOutputHeaders(true);
		shuffle.process(new File(filesPath.getShuffledBaseFile()));
	}

	public void segregate(String source){
		SegregateCSV seg = new SegregateCSV();
		seg.getTargets().add(new SegregateTargetPercent(new File(filesPath.getTrainingFile()), 75));
		seg.getTargets().add(new SegregateTargetPercent(new File(filesPath.getEvaluationFile()), 25));
		seg.setProduceOutputHeaders(true);
		seg.analyze(new File(source), true, CSVFormat.ENGLISH);
		seg.process();
	}

	public void normalize(List<Feature> featuresList){
		// Analyst
		EncogAnalyst analyst = new EncogAnalyst();

		// Wizard
		AnalystWizard wizard = new AnalystWizard(analyst);
		wizard.wizard(new File(filesPath.getBaseFile()), true, AnalystFileFormat.DECPNT_COMMA);

		for(int i = 0;i < featuresList.size();i++){
			System.out.println(i);
			analyst.getScript().getNormalize().getNormalizedFields().get(i).setAction(getType(featuresList.get(i).getType()));
		}

		//Norm for training
		AnalystNormalizeCSV norm = new AnalystNormalizeCSV();
		norm.setProduceOutputHeaders(true);
		
		norm.analyze(new File(filesPath.getTrainingFile()), true, CSVFormat.ENGLISH, analyst);
		norm.normalize(new File(filesPath.getNormalizedTrainingFile()));

		//Norm for evaluation
		norm.analyze(new File(filesPath.getEvaluationFile()), true, CSVFormat.ENGLISH, analyst);
		norm.normalize(new File(filesPath.getNormalizedEvaluationFile()));

		// Save the analyst file
		analyst.save(new File(filesPath.getAnalystFile()));
	}
	
	public void createNetwork(String networkFile) throws FileNotFoundException{
		BasicNetwork network = new BasicNetwork();
		Scanner scanner = new Scanner(new File(filesPath.getNormalizedTrainingFile()));
		String csvHeader = scanner.nextLine();
		String features[] = csvHeader.split("\\s*,\\s*");
		scanner.close();
		network.addLayer(new BasicLayer(new ActivationLinear(), true, features.length - 1));
		network.addLayer(new BasicLayer(new ActivationTANH(), true, 6));
		network.addLayer(new BasicLayer(new ActivationTANH(), true, 1));
		network.getStructure().finalizeStructure();
		network.reset();
		EncogDirectoryPersistence.saveObject(new File(networkFile), network);
	}
	
	public List<Double> trainNetwork(){
		BasicNetwork network = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(filesPath.getTrainedNetworkFile()));
		MLDataSet trainingSet = EncogUtility.loadCSV2Memory(filesPath.getNormalizedTrainingFile(), 
				network.getInputCount(), network.getOutputCount(), true, CSVFormat.ENGLISH, false);

		ResilientPropagation train = new ResilientPropagation(network, trainingSet);
		List<Double> errors = new ArrayList<>();
		int epoch = 1;
		do{
			train.iteration();
			System.out.println("epoch: " + epoch + " error: " + train.getError());
			errors.add(train.getError());
			epoch++;
		}while(train.getError() > 0.01);
		EncogDirectoryPersistence.saveObject(new File(filesPath.getTrainedNetworkFile()), network);
		return errors;
	}

	private NormalizationAction getType(String type){
		NormalizationAction action = NormalizationAction.Normalize;
		switch (type) {
		case "Continuous":
			action = NormalizationAction.Normalize;
			break;
		case "Not Continuous":
			action = NormalizationAction.Equilateral;
			break;
		case "Ignore":
			action = NormalizationAction.Ignore;
			break;
		default:
			break;
		}
		return action;
	}
}
