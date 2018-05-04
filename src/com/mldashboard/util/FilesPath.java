package com.mldashboard.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilesPath {
	
	private String basePath;
	
	private String baseFile;
	
	private String shuffledBaseFile;
	
	private String trainingFile;
	
	private String evaluationFile;
	
	private String normalizedTrainingFile;
	
	private String normalizedEvaluationFile;
	
	private String analystFile;
	
	private String trainedNetworkFile;
	
	public FilesPath(){
		String fileName = "data";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		basePath = FilesPath.class.getResource("/com/mldashboard/data/").getPath();
		baseFile = basePath + fileName + "-" + date + ".csv";
		shuffledBaseFile = basePath + fileName + "-shuffled" + date + ".csv";
		trainingFile = basePath +  fileName + "-train" + date + ".csv";
		evaluationFile = basePath +  fileName + "-eval" + date + ".csv";
		normalizedEvaluationFile = basePath +  fileName + "-evalNormalized" + date + ".csv";
		normalizedTrainingFile = basePath +  fileName + "-trainNormalized" + date + ".csv";
		analystFile = basePath +  fileName + "-analyst" + date + ".ega";
		trainedNetworkFile = basePath + fileName + "-network" + date + ".eg";
		
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(String baseFile) {
		this.baseFile = baseFile;
	}

	public String getShuffledBaseFile() {
		return shuffledBaseFile;
	}

	public void setShuffledBaseFile(String shuffledBaseFile) {
		this.shuffledBaseFile = shuffledBaseFile;
	}

	public String getTrainingFile() {
		return trainingFile;
	}

	public void setTrainingFile(String trainingFile) {
		this.trainingFile = trainingFile;
	}

	public String getEvaluationFile() {
		return evaluationFile;
	}

	public void setEvaluationFile(String evaluationFile) {
		this.evaluationFile = evaluationFile;
	}

	public String getNormalizedTrainingFile() {
		return normalizedTrainingFile;
	}

	public void setNormalizedTrainingFile(String normalizedTrainingFile) {
		this.normalizedTrainingFile = normalizedTrainingFile;
	}

	public String getNormalizedEvaluationFile() {
		return normalizedEvaluationFile;
	}

	public void setNormalizedEvaluationFile(String normalizedEvaluationFile) {
		this.normalizedEvaluationFile = normalizedEvaluationFile;
	}

	public String getAnalystFile() {
		return analystFile;
	}

	public void setAnalystFile(String analystFile) {
		this.analystFile = analystFile;
	}

	public String getTrainedNetworkFile() {
		return trainedNetworkFile;
	}

	public void setTrainedNetworkFile(String trainedNetworkFile) {
		this.trainedNetworkFile = trainedNetworkFile;
	}
	
}
