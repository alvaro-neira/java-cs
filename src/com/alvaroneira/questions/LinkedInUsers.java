package com.alvaroneira.questions;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LinkedInUsers {
    public static final String ESCAPED_DELIMITER = "\\|";
    public static final String DELIMITER = "|";
    public static final String FILEOUT = "people.out";
    public static final int LIMIT = 100;
    public static final int MEMLIMIT = 1000;

    private static class MaxHeapComparator implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            return r1.compareTo(r2);
        }
    }

    public static void externalSort(ArrayList<String> fileNames) throws IOException {
        Integer heapSize = fileNames.size();
        int[] currentIndexes = new int[heapSize];
        Comparator<Record> maxHeapComparator = new MaxHeapComparator();
        PriorityQueue<Record> heap = new PriorityQueue<>(MEMLIMIT, maxHeapComparator);
        ArrayList<BufferedReader> readers = new ArrayList<BufferedReader>();
        for (Integer j = 0; j < heapSize; j++) {
            BufferedReader br = new BufferedReader(new FileReader(fileNames.get(j)));
            heap.add(new Record(br.readLine(), ESCAPED_DELIMITER, j));
            readers.add(br);
        }
        BufferedWriter retVal = new BufferedWriter(new FileWriter(FILEOUT));
        while (!heap.isEmpty()) {
            Record popped = heap.poll();
            retVal.write(popped.getFullLine());
            retVal.newLine();
            if (currentIndexes[popped.getSortId()] < (MEMLIMIT - 1)) {
                currentIndexes[popped.getSortId()]++;
                String line = readers.get(popped.getSortId()).readLine();
                if (line != null) {
                    heap.add(new Record(line, ESCAPED_DELIMITER, popped.getSortId()));
                }
            }
        }
        retVal.close();
    }

    private static ArrayList<String> internalSort(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<Record> records = new ArrayList<Record>();
        String line;
        Integer nLines = 0;
        ArrayList<String> auxFiles = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            Record record = new Record(line, ESCAPED_DELIMITER);
            records.add(record);
            nLines++;
            if (nLines == MEMLIMIT) {
                records.sort(Record::compareTo);
                String newFile = "_swa" + (auxFiles.size() + 1);
                auxFiles.add(newFile);
                BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                for (int i = 0; i < records.size(); i++) {
                    bw.write(records.get(i).getFullLine());
                    bw.newLine();
                }
                bw.close();
                nLines = 0;
                records = new ArrayList<Record>();
            }
        }
        br.close();
        records.sort(Record::compareTo);
        String newFile = "_swa" + (auxFiles.size() + 1);
        auxFiles.add(newFile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
        for (int i = 0; i < records.size(); i++) {
            bw.write(records.get(i).getFullLine());
            bw.newLine();
        }
        bw.close();
        return auxFiles;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java LinkedInUsers people.in");
            System.exit(-1);
        }
        try {
            ArrayList<String> auxFiles = internalSort(args[0]);
            externalSort(auxFiles);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file '" + args[0] + "'");
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Couldn't read '" + args[0] + "'");
            System.err.println(e);
        }
    }

    static class Record implements Comparable<Record> {
        private Integer sortId;
        public String personId;
        public String name;
        public String lastName;
        public String currentRole;
        public String country;
        public String industry;
        public Integer numberOfRecommendations;
        public Integer numberOfConnections;

        public Record(String line, String delimiter, Integer id) throws IOException {
            this(line, delimiter);
            this.sortId = id;
        }

        public Record(String line, String delimiter) throws IOException {
            String[] values = line.split(delimiter);
            if (values.length != 8 || values[0].trim().isEmpty()) {
                throw new IOException("Invalid line '" + line + "'");
            }
            this.personId = values[0].trim();
            this.name = values[1].trim();
            this.lastName = values[2].trim();
            this.currentRole = values[3].trim();
            this.country = values[4].trim();
            this.industry = values[5].trim();
            this.numberOfRecommendations = Integer.parseInt(values[6]);
            this.numberOfConnections = Integer.parseInt(values[7]);
        }

        @Override
        public int compareTo(Record r) {
            return this.name == null ? -1 : this.name.compareTo(r.name);
        }

        /**
         * @return
         */
        public String getFullLine() {
            return this.personId + DELIMITER +
                    this.name + DELIMITER +
                    this.lastName + DELIMITER +
                    this.currentRole + DELIMITER +
                    this.country + DELIMITER +
                    this.industry + DELIMITER +
                    this.numberOfRecommendations + DELIMITER +
                    this.numberOfConnections;
        }

        public Integer getSortId() {
            return sortId;
        }
    }
}


