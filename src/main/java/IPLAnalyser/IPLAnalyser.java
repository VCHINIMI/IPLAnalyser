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
