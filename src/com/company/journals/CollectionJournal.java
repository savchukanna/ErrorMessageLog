package com.company.journals;

import com.company.errors.OutOfRangeException;
import com.company.record.Record;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CollectionJournal implements Journal {
        private List<Record> recordsList;

        public CollectionJournal() {
            recordsList = new ArrayList<>();
        }

        public CollectionJournal(Journal journal){
            recordsList = new ArrayList<Record>();
            for(int i = 0; i < journal.size(); i++){
                recordsList.add(journal.get(i));
            }
        }

        @Override
        public void add(Record r) {
            recordsList.add(r);
        }

        @Override
        public void add(Journal j) {
            for (int i = 0; i < j.size(); i++){
                recordsList.add(j.get(i));
            }
        }

        @Override
        public void remove(Record r) {
            recordsList.remove(r);
        }

        @Override
        public Record get(int index)  {
            return recordsList.get(index);
        }

        @Override
        public void set(int index, Record record) {
            recordsList.set(index, record);
        }

        @Override
        public void insert(int index, Record record) {
            recordsList.add(index, record);
        }

        @Override
        public void remove(int index) {
            recordsList.remove(index);
        }

        @Override
        public void remove(int fromIndex, int toIndex) {
            try{
                if (fromIndex > toIndex) throw new OutOfRangeException(fromIndex, toIndex);
            } catch (OutOfRangeException e) {
                System.out.println(e.toString());
                System.out.println("Exception in CollectionJournal.remove(fromIndex, toIndex)");
            }
            for (int i = fromIndex; i < toIndex; i++) {
                recordsList.remove(fromIndex);
            }
        }

        @Override
        public void removeAll() {
            remove(0, recordsList.size());
        }

        @Override
        public int size() {
            return recordsList.size();
        }

        @Override
        public Journal filter(String s) {
            Journal journal = new CollectionJournal();
            recordsList.stream().filter(r->r.toString().equals(s)).forEach(journal::add);
            return journal;
        }

        @Override
        public Journal filter(Date fromDate, Date toDate) {
            Journal journal = new CollectionJournal();
            recordsList.stream().filter(r -> r.getTime().after(fromDate) && r.getTime().before(toDate)).forEach(journal::add);
            return journal;
        }

        @Override
        public void sortByDate() {
            if (recordsList.size() < 2) return;
            recordsList.sort(Comparator.comparing(Record::getTime));
       }

       @Override
       public void sortByImportanceDate() {
           if (recordsList.size() < 2) return;
           recordsList.sort(Comparator.comparing(Record::getImportance).thenComparing(Record::getTime));
       }

        @Override
        public void sortByImportanceSourceDate() {
            if (recordsList.size() < 2) return;
            recordsList.sort(Comparator.comparing(Record::getImportance)
                    .thenComparing(Record::getSource).thenComparing(Record::getTime));
        }

        @Override
        public void sortBySourceDate() {
            if (recordsList.size() < 2) return;
            recordsList.sort(Comparator.comparing(Record::getSource).thenComparing(Record::getTime));
        }

        @Override
        public void printRecords() {
            recordsList.forEach(System.out::println);
        }
}