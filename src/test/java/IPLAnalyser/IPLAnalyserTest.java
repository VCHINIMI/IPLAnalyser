package IPLAnalyser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
	public String IPL_RUNS_FILEPATH = "C:\\Users\\vinay\\Desktop\\myWorkSpace\\IPLAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
	public String IPL_WICKETS_FILEPATH = "C:\\Users\\vinay\\Desktop\\myWorkSpace\\IPLAnalyser\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
	public IPLAnalyser iplAnalyser;

	@Before
	public void intitializeTest() {
		iplAnalyser = new IPLAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(IPLException.class);
	}

//	Highest Strike Rate = Ishant Sharma
	@Test
	public void highestStrikeRateBatsmentest() throws IPLException {
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		List<IPLRunsCSV> batsmenList = iplAnalyser.highestStrikeRate();
		assertEquals(batsmenList.get(0).playerName, "Ishant Sharma");
	}

//	Highest Batting Average = MS Dhoni
	@Test
	public void highestBattingAverageBatsmentest() throws IPLException {
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		List<IPLRunsCSV> batsmenList = iplAnalyser.sortByBattingAverage();
		assertEquals(batsmenList.get(0).playerName, "MS Dhoni");
	}

//	Maximum Number of Sixes And Fours = Andre Russell
	@Test
	public void MaximumNumberofSixesAndFours() throws IPLException {
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		List<IPLRunsCSV> batsmenList = iplAnalyser.sortByBoundaries();
		assertEquals(batsmenList.get(0).playerName, "Andre Russell");
	}

//	Best Average && best Strike Rate	
	@Test
	public void bestAverageThenStrikeRate() throws IPLException {
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		List<IPLRunsCSV> batsmenList = iplAnalyser.sortByAverageAndStrikerate();
		assertEquals(batsmenList.get(0).playerName, "MS Dhoni");
	}

//	Max Runs && best Average
	@Test
	public void MaxRunsThenBestAverage() throws IPLException {
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		List<IPLRunsCSV> batsmenList = iplAnalyser.sortByMaximumRunsAndAverage();
		assertEquals(batsmenList.get(0).playerName, "David Warner ");
	}

//	Sort Bowlers by best Average = Krishnappa Gowtham
	@Test
	public void sortByBestBowlingAverage() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		IPLWicketsCSV bowler = iplAnalyser.sortByBowlingAverage();
		assertEquals(bowler.playerName, "Anukul Roy");
	}
	
//	Sort Bowlers by best Strike rate = Krishnappa Gowtham
	@Test
	public void sortByBestStrikeRate() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		IPLWicketsCSV bowler = iplAnalyser.sortByBowlingStrikeRate();
		assertEquals(bowler.playerName, "Alzarri Joseph");
	}
	
//	Sort Bowlers by best Economy = Shivam Dube
	@Test
	public void sortByBestEconomy() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		List<IPLWicketsCSV> bowlerList = iplAnalyser.sortByBowlingEconomy();
		assertEquals(bowlerList.get(0).playerName, "Shivam Dube");
	}
	
//	Sort Bowlers by best Strike rate and 4w and 5w = Alzarri Joseph
	@Test
	public void sortByBestStrikeRateAnd4w5w() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		IPLWicketsCSV bowler = iplAnalyser.sortByBowlingStrikeRateAnd5w();
		assertEquals(bowler.playerName, "Alzarri Joseph");
	}
	
//	Sort Bowlers by best Bowling avg and best strike rate = Anukul Roy
	@Test
	public void sortByBestBowlingAvgRateAndStrikeRate() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		IPLWicketsCSV bowler = iplAnalyser.sortByBestBowlingAvgRateAndStrikeRate();
		assertEquals(bowler.playerName, "Anukul Roy");
	}
	
//	Best batting and bowling averages
	@Test
	public void findBestBattingandBowlingAverages() throws IPLException {
		iplAnalyser.loadIPLBowlerData(IPL_WICKETS_FILEPATH);
		iplAnalyser.loadIPLBatsmenData(IPL_RUNS_FILEPATH);
		assertEquals(iplAnalyser.bestBattingAndBowlingAverages(), "Umesh Yadav");
	}
	
}
