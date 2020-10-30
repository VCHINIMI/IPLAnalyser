package IPLAnalyser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
	public String IPL_RUNS_FILEPATH = "C:\\Users\\vinay\\Desktop\\myWorkSpace\\IPLAnalyser\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
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
}
