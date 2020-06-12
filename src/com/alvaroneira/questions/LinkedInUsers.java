package com.alvaroneira.questions;

import java.io.*;
import java.util.*;

public class LinkedInUsers {
    public static final String ESCAPED_DELIMITER = "\\|";
    public static final String DELIMITER = "|";
    public static final int LIMIT = 100;
    public static final int MEMLIMIT = 501;
    public static HashMap<String, Integer> industryCriteria;
    public static HashMap<String, Integer> roleCriteria;
    public static HashMap<String, Integer> countryCriteria;

    /**
     *
     */
    public static class Ranker implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            Integer ranking = 0;
            if (ranking == 0) {
                Integer industry1 = industryCriteria.get(r1.industry);
                Integer industry2 = industryCriteria.get(r2.industry);
                ranking = (industry2 != null ? industry2 : 0) - (industry1 != null ? industry1 : 0);
            }
            if (ranking == 0) {
                Integer role1 = 0;
                Integer role2 = 0;
                Iterator it = roleCriteria.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    String keyword = (String) pair.getKey();
                    Integer score = (Integer) pair.getValue();
                    if (r1.currentRole.contains(keyword)) {
                        role1 += score;
                    }
                    if (r2.currentRole.contains(keyword)) {
                        role2 += score;
                    }
                }
                ranking = role2 - role1;
            }
            if (ranking == 0) {
                Integer country1 = countryCriteria.get(r1.country);
                Integer country2 = countryCriteria.get(r2.country);
                ranking = (country2 != null ? country2 : 0) - (country1 != null ? country1 : 0);
            }
            if (ranking == 0) {
                ranking = r2.numberOfConnections - r1.numberOfConnections;
            }
            if (ranking == 0) {
                ranking = r2.numberOfRecommendations - r1.numberOfRecommendations;
            }
            return ranking;
        }
    }

    public static void fillRoleCriteria() {
        roleCriteria = new HashMap<String, Integer>();
        roleCriteria.put("lead", 20);
        roleCriteria.put("manager", 20);
        roleCriteria.put("president", 10);
        roleCriteria.put("software", 100);
        roleCriteria.put("vp", 10);
    }

    public static void fillIndustryCriteria() {
        industryCriteria = new HashMap<String, Integer>();
        industryCriteria.put("Accounting", 10);
        industryCriteria.put("Animation", 10);
        industryCriteria.put("Architecture & Planning", 10);
        industryCriteria.put("Banking", 20);
        industryCriteria.put("Biotechnology", 10);
        industryCriteria.put("Broadcast Media", 10);
        industryCriteria.put("Civil Engineering", 20);
        industryCriteria.put("Computer & Network Security", 100);
        industryCriteria.put("Computer Games", 100);
        industryCriteria.put("Computer Hardware", 90);
        industryCriteria.put("Computer Networking", 100);
        industryCriteria.put("Computer Software", 100);
        industryCriteria.put("Construction", 10);
        industryCriteria.put("Consumer Electronics", 10);
        industryCriteria.put("Consumer Goods", 10);
        industryCriteria.put("Consumer Services", 10);
        industryCriteria.put("Design", 10);
        industryCriteria.put("E-Learning", 90);
        industryCriteria.put("Education Management", 10);
        industryCriteria.put("Electrical/Electronic Manufacturing", 20);
        industryCriteria.put("Electronics", 10);
        industryCriteria.put("Entertainment", 10);
        industryCriteria.put("Facilities Services", 10);
        industryCriteria.put("Financial Services", 10);
        industryCriteria.put("Food Production", 10);
        industryCriteria.put("Fund-Raising", 10);
        industryCriteria.put("Government Administration", 10);
        industryCriteria.put("Government Relations", 10);
        industryCriteria.put("Graphic Design", 50);
        industryCriteria.put("Human Resources", 10);
        industryCriteria.put("Import and Export", 10);
        industryCriteria.put("Industrial Automation", 10);
        industryCriteria.put("Information Services", 90);
        industryCriteria.put("Information Technology and Services", 100);
        industryCriteria.put("Insurance", 10);
        industryCriteria.put("Internet", 90);
        industryCriteria.put("Investment Banking", 10);
        industryCriteria.put("Investment Management", 10);
        industryCriteria.put("Legal Services", 10);
        industryCriteria.put("Leisure, Travel & Tourism", 10);
        industryCriteria.put("Libraries", 10);
        industryCriteria.put("Logistics and Supply Chain", 10);
        industryCriteria.put("Machinery", 10);
        industryCriteria.put("Management Consulting", 10);
        industryCriteria.put("Manufacturing", 10);
        industryCriteria.put("Market Research", 20);
        industryCriteria.put("Marketing and Advertising", 10);
        industryCriteria.put("Mechanical or Industrial Engineering", 10);
        industryCriteria.put("Media Production", 20);
        industryCriteria.put("Medical Devices", 10);
        industryCriteria.put("Medical Practice", 10);
        industryCriteria.put("Mining & Metals", 10);
        industryCriteria.put("Motor Vehicle Dealers", 10);
        industryCriteria.put("Newspapers", 10);
        industryCriteria.put("Nonprofit Organization Management", 10);
        industryCriteria.put("Oil & Energy", 10);
        industryCriteria.put("Online Media", 10);
        industryCriteria.put("Package/Freight Delivery", 10);
        industryCriteria.put("Pharmaceuticals", 10);
        industryCriteria.put("Printing", 10);
        industryCriteria.put("Professional Training & Coaching", 10);
        industryCriteria.put("Program Development", 10);
        industryCriteria.put("Public Relations and Communications", 10);
        industryCriteria.put("Publishing", 10);
        industryCriteria.put("Real Estate", 10);
        industryCriteria.put("Research", 10);
        industryCriteria.put("Retail", 10);
        industryCriteria.put("Security Products & Services", 10);
        industryCriteria.put("Security and Investigations", 10);
        industryCriteria.put("Staffing and Recruiting", 10);
        industryCriteria.put("Telecommunications", 90);
        industryCriteria.put("Tires & Rubber", 10);
        industryCriteria.put("Translation and Localization", 10);
        industryCriteria.put("Transportation/Trucking/Railroad", 10);
        industryCriteria.put("Warehousing", 10);
    }

    public static void fillCountryCriteria() {
        countryCriteria=new HashMap<String,Integer>();
        countryCriteria.put("Argentina", 100);
        countryCriteria.put("Australia", 10);
        countryCriteria.put("Austria", 10);
        countryCriteria.put("Belgium", 10);
        countryCriteria.put("Brazil", 100);
        countryCriteria.put("Canada", 10);
        countryCriteria.put("Chile", 100);
        countryCriteria.put("China", 80);
        countryCriteria.put("Colombia", 100);
        countryCriteria.put("Costa Rica", 100);
        countryCriteria.put("Denmark", 10);
        countryCriteria.put("Ecuador", 10);
        countryCriteria.put("Finland", 10);
        countryCriteria.put("France", 10);
        countryCriteria.put("Germany", 10);
        countryCriteria.put("Hong Kong", 10);
        countryCriteria.put("India", 10);
        countryCriteria.put("Ireland", 10);
        countryCriteria.put("Israel", 10);
        countryCriteria.put("Italy", 10);
        countryCriteria.put("Japan", 10);
        countryCriteria.put("Korea", 10);
        countryCriteria.put("Luxembourg", 10);
        countryCriteria.put("Mexico", 100);
        countryCriteria.put("Netherlands", 10);
        countryCriteria.put("New Zealand", 10);
        countryCriteria.put("Norway", 10);
        countryCriteria.put("Panama", 100);
        countryCriteria.put("Peru", 100);
        countryCriteria.put("Portugal", 10);
        countryCriteria.put("Puerto Rico", 100);
        countryCriteria.put("South Africa", 10);
        countryCriteria.put("Spain", 50);
        countryCriteria.put("Sweden", 10);
        countryCriteria.put("Switzerland", 10);
        countryCriteria.put("Taiwan", 10);
        countryCriteria.put("United Kingdom", 10);
        countryCriteria.put("United States", 90);
        countryCriteria.put("Uruguay", 100);
        countryCriteria.put("Venezuela", 100);
    }

    public static class Record {
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

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java LinkedInUsers people.in");
            System.exit(-1);
        }
        try {
            fillIndustryCriteria();
            fillRoleCriteria();
            fillCountryCriteria();
            String baseName = (new File(args[0])).getName().replaceFirst("[.][^.]+$", "");
            ArrayList<String> auxFiles = internalSort(args[0], baseName);
            externalSort(auxFiles, baseName);
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file '" + args[0] + "'");
            System.err.println(e);
        } catch (IOException e) {
            System.err.println("Couldn't read '" + args[0] + "'");
            System.err.println(e);
        }
    }

    /**
     * @param fileNames
     * @param outName
     * @throws IOException
     */
    private static void externalSort(ArrayList<String> fileNames, String outName) throws IOException {
        Integer heapSize = fileNames.size();
        Comparator<Record> maxHeapComparator = new Ranker();
        PriorityQueue<Record> heap = new PriorityQueue<>(MEMLIMIT, maxHeapComparator);
        ArrayList<BufferedReader> readers = new ArrayList<BufferedReader>();
        for (Integer j = 0; j < heapSize; j++) {
            BufferedReader br = new BufferedReader(new FileReader(fileNames.get(j)));
            String line = br.readLine();
            if (line != null) {
                heap.add(new Record(line, ESCAPED_DELIMITER, j));
            } else {
                System.err.println("Empty file '" + fileNames.get(j) + "'");
            }
            readers.add(br);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outName + ".out"));
        int nLines = 0;
        while (!heap.isEmpty() && nLines < LIMIT) {
            Record popped = heap.poll();
            writer.write(popped.personId);
            writer.newLine();
            String line = readers.get(popped.getSortId()).readLine();
            if (line != null) {
                heap.add(new Record(line, ESCAPED_DELIMITER, popped.getSortId()));
            }
            nLines++;
        }
        for (Integer j = 0; j < heapSize; j++) {
            readers.get(j).close();
            (new File(fileNames.get(j))).delete();
        }
        writer.close();
    }

    /**
     * @param fileName
     * @param baseName
     * @return
     * @throws IOException
     */
    private static ArrayList<String> internalSort(String fileName, String baseName) throws IOException {
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
                records.sort(new Ranker());
                String newFile = "_" + baseName + "_" + (auxFiles.size() + 1);
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
        records.sort(new Ranker());
        String newFile = "_" + baseName + "_" + (auxFiles.size() + 1);
        auxFiles.add(newFile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
        for (int i = 0; i < records.size(); i++) {
            bw.write(records.get(i).getFullLine());
            bw.newLine();
        }
        bw.close();
        return auxFiles;
    }
}


