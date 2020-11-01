package IPLAnalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketsCSV {

	@CsvBindByName(column = "PLAYER")
	public String playerName;

	@CsvBindByName(column = "Runs")
	public int totalRuns;

	@CsvBindByName(column = "Avg")
	public double bowlingAverage;

	@CsvBindByName(column = "SR")
	public double strikeRate;

	@CsvBindByName(column = "Econ")
	public double economyRate;

	@CsvBindByName(column = "5w")
	public int wicketHaul_5;

	@CsvBindByName(column = "4w")
	public int wicketHaul_4;

	@CsvBindByName(column = "Wkts")
	public int totalWickets;
}
