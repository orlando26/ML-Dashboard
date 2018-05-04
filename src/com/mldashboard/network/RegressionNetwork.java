package com.mldashboard.network;

import java.io.File;

import org.encog.app.analyst.csv.segregate.SegregateCSV;
import org.encog.app.analyst.csv.segregate.SegregateTargetPercent;
import org.encog.app.analyst.csv.shuffle.ShuffleCSV;
import org.encog.util.csv.CSVFormat;

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
}
