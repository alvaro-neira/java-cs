package com.alvaroneira.questions;

import java.io.*;
import java.util.ArrayList;

public class LinkedInUsers {
    public static final String DELIMITER = "\\|";
    public static final String FILEOUT = "people.out";
    public static final Integer LIMIT = 100;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java LinkedInUsers people.in");
            System.exit(-1);
        }
        ArrayList<Record> records = new ArrayList<Record>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = br.readLine()) != null) {
                Record record = new Record(line, DELIMITER);
                records.add(record);
            }
            br.close();
            records.sort(Record::compareTo);
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILEOUT));
            for (int i = 0; i < records.size() && i < LIMIT; i++) {
                bw.write(records.get(i).personId);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file '" + args[0] + "'");
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Couldn't read '" + args[0] + "'");
            System.err.println(e);
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
}
