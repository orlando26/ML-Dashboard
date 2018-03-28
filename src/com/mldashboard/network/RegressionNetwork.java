package com.mldashboard.network;

import java.io.File;

import org.encog.app.analyst.csv.segregate.SegregateCSV;
import org.encog.app.analyst.csv.segregate.SegregateTargetPercent;
import org.encog.app.analyst.csv.shuffle.ShuffleCSV;
import org.encog.util.csv.CSVFormat;


public class RegressionNetwork {
	public static void shuffle(String source){
		// Shuffle the base file
		ShuffleCSV shuffle = new ShuffleCSV();
		shuffle.analyze(new File(source), true, CSVFormat.ENGLISH);
		shuffle.setProduceOutputHeaders(true);
		shuffle.process(new File(FilesPath.SHUFFLED_BASE_FILE));
	}

	public static void segregate(String source){
		SegregateCSV seg = new SegregateCSV();
		seg.getTargets().add(new SegregateTargetPercent(new File(FilesPath.TRAINING_FILE), 75));
		seg.getTargets().add(new SegregateTargetPercent(new File(FilesPath.EVALUATION_FILE), 25));
		seg.setProduceOutputHeaders(true);
		seg.analyze(new File(source), true, CSVFormat.ENGLISH);
		seg.process();
	}
}
