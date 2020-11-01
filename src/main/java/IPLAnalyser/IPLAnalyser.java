package IPLAnalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import CSVBuilder.*;

public class IPLAnalyser {

	List<IPLRunsCSV> batsmenList = null;
	List<IPLWicketsCSV> bowlerList = null;

//	LOAD IPL BATSMEN DATA INTO LIST USING OPENCSV
	public List<IPLRunsCSV> loadIPLBatsmenData(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			batsmenList = csvBuilder.getCSVFileList(reader, IPLRunsCSV.class);
			return batsmenList;
		} catch (CSVBuilderException e) {
			throw new IPLException(e.getMessage(), e.type.name());
		} catch (IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.IPl_FILE_PROBLEM);
		} catch (Exception e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_PROBLEM);
		}
	}

//	LOAD IPL BOWLER DATA INTO LIST USING OPENCSV
	public List<IPLWicketsCSV> loadIPLBowlerData(String csvFilePath) throws IPLException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			bowlerList = csvBuilder.getCSVFileList(reader, IPLWicketsCSV.class);
			return bowlerList;
		} catch (CSVBuilderException e) {
			throw new IPLException(e.getMessage(), e.type.name());
		} catch (IOException e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.IPl_FILE_PROBLEM);
		} catch (Exception e) {
			throw new IPLException(e.getMessage(), IPLException.ExceptionType.CSV_FILE_PROBLEM);
		}
	}

//	COMPARE BATSMEN BY STRIKERATE 
	public List<IPLRunsCSV> highestStrikeRate() {
		Comparator<IPLRunsCSV> comparator = Comparator.comparing(Batsmen -> Batsmen.strikeRate);
		return batsmenList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

//	COMPARE BATSMEN BY BATTING-AVERAGE
	public List<IPLRunsCSV> sortByBattingAverage() {
		Comparator<IPLRunsCSV> comparator = Comparator.comparing(Batsmen -> Batsmen.battingAverage);
		return batsmenList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

//	COMPARE BY BOUNDARIES (4'S AND 6'S)	
	public List<IPLRunsCSV> sortByBoundaries() {
		Comparator<IPLRunsCSV> comparator = Comparator.comparing(Batsmen -> Batsmen.noOfBoundaries + Batsmen.noOfSixes);
		this.batsmenList.sort(comparator);
		Collections.reverse(batsmenList);
		return batsmenList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

//	GREAT AVG WITH BEST STRIKE RATE POSSIBLE
	public List<IPLRunsCSV> sortByAverageAndStrikerate() {
		Comparator<IPLRunsCSV> averageComparator = Comparator.comparing(Batsmen -> Batsmen.battingAverage);
		Comparator<IPLRunsCSV> strikerateComparator = Comparator.comparing(Batsmen -> Batsmen.strikeRate);
		Comparator<IPLRunsCSV> comparator = averageComparator.thenComparing(strikerateComparator);
		this.batsmenList.sort(comparator);
		Collections.reverse(batsmenList);
		return batsmenList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

//	GREAT MAX RUNS WITH BEST AVG POSSIBLE
	public List<IPLRunsCSV> sortByMaximumRunsAndAverage() {
		Comparator<IPLRunsCSV> averageComparator = Comparator.comparing(Batsmen -> Batsmen.totalRuns);
		Comparator<IPLRunsCSV> strikerateComparator = Comparator.comparing(Batsmen -> Batsmen.battingAverage);
		Comparator<IPLRunsCSV> comparator = averageComparator.thenComparing(strikerateComparator);
		this.batsmenList.sort(comparator);
		Collections.reverse(batsmenList);
		return batsmenList.stream().sorted(comparator.reversed()).collect(Collectors.toList());
	}

// TOP BOWLING AVERAGE
	public IPLWicketsCSV sortByBowlingAverage() {
		Comparator<IPLWicketsCSV> comparator = Comparator.comparing(bowler -> bowler.bowlingAverage);
		bowlerList = bowlerList.stream().sorted(comparator).collect(Collectors.toList());
		for (IPLWicketsCSV bowler : bowlerList) {
			if (bowler.bowlingAverage != 0)
				return bowler;
		}
		return null;
	}

//	TOP STRIKERATE BOWLER
	public IPLWicketsCSV sortByBowlingStrikeRate() {
		Comparator<IPLWicketsCSV> comparator = Comparator.comparing(bowler -> bowler.strikeRate);
		bowlerList = bowlerList.stream().sorted(comparator).collect(Collectors.toList());
		for (IPLWicketsCSV bowler : bowlerList) {
			if (bowler.bowlingAverage != 0)
				return bowler;
		}
		return null;
	}

//	TOP ECONOMY BOWLER	
	public List<IPLWicketsCSV> sortByBowlingEconomy() {
		Comparator<IPLWicketsCSV> comparator = Comparator.comparing(bowler -> bowler.economyRate);
		return bowlerList.stream().sorted(comparator).collect(Collectors.toList());
	}

//	BEST STRIKE RATE AND HAS 4W AND 5 W	
	public IPLWicketsCSV sortByBowlingStrikeRateAnd5w() {
		Comparator<IPLWicketsCSV> strikeRateComparator = Comparator.comparing(bowler -> bowler.strikeRate);
		Comparator<IPLWicketsCSV> wicketsComparator = Comparator.comparing(bowler -> bowler.wicketHaul_5);
		Comparator comparator = wicketsComparator.reversed().thenComparing(strikeRateComparator);
		bowlerList = (List<IPLWicketsCSV>) bowlerList.stream().sorted(comparator).collect(Collectors.toList());
		return bowlerList.get(0);
	}

//	BEST AVG AND BEST STRIKE RATE	
	public IPLWicketsCSV sortByBestBowlingAvgRateAndStrikeRate() {
		Comparator<IPLWicketsCSV> bowlingAvgComparator = Comparator.comparing(bowler -> bowler.bowlingAverage);
		Comparator<IPLWicketsCSV> strikeRateComparator = Comparator.comparing(bowler -> bowler.strikeRate);
		Comparator comparator = bowlingAvgComparator.thenComparing(strikeRateComparator);
		bowlerList = (List<IPLWicketsCSV>) bowlerList.stream().sorted(comparator).collect(Collectors.toList());
		for (IPLWicketsCSV bowler : bowlerList) {
			if (bowler.bowlingAverage != 0)
				return bowler;
		}
		return null;
	}

//	BEST BATTING AND BOWLING AVERAGES
	public String bestBattingAndBowlingAverages() {
		Comparator<IPLWicketsCSV> comparator = Comparator.comparing(bowler -> bowler.bowlingAverage);
		bowlerList = bowlerList.stream().sorted(comparator).collect(Collectors.toList());
		batsmenList = this.sortByBattingAverage();
		for (int i = 0; i < (bowlerList.size() > batsmenList.size() ? batsmenList.size() - 1
				: bowlerList.size() - 1); i++) {
			if (bowlerList.get(i).bowlingAverage != 0)
				if (bowlerList.get(i).playerName.equals(batsmenList.get(i).playerName))
					return bowlerList.get(i).playerName;
		}
		return null;
	}

//	MAX HUNDREDS AND BEST BATTING AVERAGE
	public IPLRunsCSV sortByMaximum100AndAverage() {
		Comparator<IPLRunsCSV> centuryComparator = Comparator.comparing(Batsmen -> Batsmen.noOfCenturies);
		Comparator<IPLRunsCSV> strikerateComparator = Comparator.comparing(Batsmen -> Batsmen.battingAverage);
		Comparator<IPLRunsCSV> comparator = centuryComparator.reversed().thenComparing(strikerateComparator.reversed());
		this.batsmenList.sort(comparator);
		return batsmenList.get(0);
	}
	
	public IPLRunsCSV sortByMinimum100AndAverage() {
		Comparator<IPLRunsCSV> centuryComparator = Comparator.comparing(Batsmen -> Batsmen.noOfCenturies);
		Comparator<IPLRunsCSV> halfCenturyComparator = Comparator.comparing(Batsmen -> Batsmen.noOfHalfCenturies);
		Comparator<IPLRunsCSV> strikerateComparator = Comparator.comparing(Batsmen -> Batsmen.battingAverage);
		Comparator<IPLRunsCSV> comparator = centuryComparator.thenComparing(halfCenturyComparator).thenComparing(strikerateComparator.reversed());
		this.batsmenList.sort(comparator);
		return batsmenList.get(0);
	}	
	
//  BUBBLE SORT METHOD 	
	private <E> void sort(Comparator<E> IPLComparator, List<E> sortList) {
		for (int i = 0; i < sortList.size() - 1; i++) {
			for (int j = 0; j < sortList.size() - i - 1; j++) {
				E census1 = sortList.get(j);
				E census2 = sortList.get(j + 1);
				if (IPLComparator.compare(census1, census2) > 0) {
					sortList.set(j, census2);
					sortList.set(j + 1, census1);
				}
			}
		}
	}
}
