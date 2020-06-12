package com.alvaroneira.questions;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LinkedInUsers {
    public static final String DELIMITER = "\\|";
    public static final String FILEOUT = "people.out";
    public static final Integer LIMIT = 100;
    public static final Integer MEMLIMIT = 123;

    private static class MinHeapComparator implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            return r1.compareTo(r2);
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java LinkedInUsers people.in");
            System.exit(-1);
        }
        ArrayList<Record> records = new ArrayList<Record>();
        String line;
        Integer nLines = 0;
        ArrayList<String> auxFiles = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            while ((line = br.readLine()) != null) {
                Record record = new Record(line, DELIMITER);
                records.add(record);
                nLines++;
                if (nLines == MEMLIMIT) {
                    mergeSort(records);
                    String newFile = "_swa" + (auxFiles.size() + 1);
                    auxFiles.add(newFile);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                    for (int i = 0; i < records.size(); i++) {
                        bw.write(records.get(i).personId);
                        bw.newLine();
                    }
                    bw.close();
                    nLines = 0;
                    records = new ArrayList<Record>();
                }
            }
            br.close();
            mergeSort(records);
            String newFile = "_swa" + (auxFiles.size() + 1);
            auxFiles.add(newFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
            for (int i = 0; i < records.size(); i++) {
                bw.write(records.get(i).personId);
                bw.newLine();
            }
            bw.close();

//
//
//
//            mergeSort(records);
//            BufferedWriter bw = new BufferedWriter(new FileWriter(FILEOUT));
//            for (int i = 0; i < records.size() && i < LIMIT; i++) {
//                bw.write(records.get(i).personId);
//                bw.write("|");
//                bw.write(records.get(i).name);
//                bw.newLine();
//            }
//            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file '" + args[0] + "'");
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Couldn't read '" + args[0] + "'");
            System.err.println(e);
        }
    }

    private static void mergeSort(ArrayList<Record> arr) {
        int l = 0;
        int r = arr.size() - 1;
        sort(arr, l, r);
    }

    private static void sort(ArrayList<Record> arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            ArrayList<Record> L = new ArrayList<Record>(n1);
            ArrayList<Record> R = new ArrayList<Record>(n2);

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i) {
                L.add(arr.get(l + i));
            }
            for (int j = 0; j < n2; ++j) {
                R.add(arr.get(m + 1 + j));
            }

            mergeSortMerge(arr, L, R, n1, n2, l);
        }
    }

    public static void mergeSortMerge(ArrayList<Record> result, ArrayList<Record> L, ArrayList<Record> R, int n1, int n2, int l) {
        // Initial index of merged subarry array
        int k = l;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
                result.set(k, L.get(i));
                i++;
            } else {
                result.set(k, R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            if (result.size() <= k) {
                result.add(L.get(i));
            } else {
                result.set(k, L.get(i));
            }
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            if (result.size() <= k) {
                result.add(R.get(j));
            } else {
                result.set(k, R.get(j));
            }
            j++;
            k++;
        }
    }

}

class Record implements Comparable<Record> {
    String personId;
    String name;
    String lastName;
    String currentRole;
    String country;
    String industry;
    Integer numberOfRecommendations;
    Integer numberOfConnections;

    public Record(String personId) {
        this.personId = personId;
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

    @Override
    public String toString(){
        return this.personId+"|"+this.name;
    }
}
