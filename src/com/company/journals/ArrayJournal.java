package com.company.journals;

import com.company.record.Record;
import com.company.errors.OutOfRangeException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class ArrayJournal implements Journal {
    private int recordSize;
    private Record[] recordsArray;

    public ArrayJournal(){
        recordsArray = new Record[8];
        recordSize = 0;
    }

    public ArrayJournal(Journal journal){
       recordsArray = new Record[journal.size()];
       recordSize = 0;
       for (int i = 0; i < journal.size(); i++) {
           recordsArray[i] = journal.get(i);
           recordSize++;
       }
    }

    @Override
    public void add(Record r) {
        if (recordSize >= recordsArray.length) {
            recordsArray = Arrays.copyOf(recordsArray, recordsArray.length + 1);
            System.arraycopy(recordsArray, 0, recordsArray, 0, recordsArray.length);
        }
        recordsArray[recordSize] = r;
        recordSize++;
    }

    @Override
    public void add(Journal j) {
        if (recordsArray.length - recordSize >= j.size()){
            for (int i = 0; i < j.size(); i++) {
                recordsArray[recordSize] = j.get(i);
                recordSize++;
            }
        } else {
            recordsArray = Arrays.copyOf(recordsArray, recordsArray.length * 2);
            System.arraycopy(recordsArray, 0, recordsArray, 0, recordsArray.length);
            add(j);
        }
    }

    @Override
    public void remove(Record r) {
        for (int i = 0; i < recordSize; i++) {
            if (r.equals(recordsArray[i])) {
                System.arraycopy(recordsArray, i + 1, recordsArray, i, recordSize - i);
                recordSize--;
            }
        }
    }

    @Override
    public Record get(int index) {
        return recordsArray[index];
    }

    @Override
    public void set(int index, Record record) {
        try {
            if (index < 0 || index >= size()) throw new OutOfRangeException(index, recordSize);
            Record that = record;
            recordsArray[index] = that;
        }catch (OutOfRangeException e) {
            System.out.println(e.outOfBounds());
            System.out.println("Exception in set method");
        }
    }

    @Override
    public void insert(int index, Record record) {
        try {
            if (index < 0 || index >= size() || record == null) throw new OutOfRangeException(index, recordSize);
            if(recordSize < recordsArray.length) {
                System.arraycopy(recordsArray, index, recordsArray, index + 1, recordSize + 1 - index);
            } else {
                recordsArray = Arrays.copyOf(recordsArray, recordsArray.length + 1);
                System.arraycopy(recordsArray, index, recordsArray, index + 1, recordsArray.length - index - 1);
            }
            recordsArray[index] = record;
        }catch (OutOfRangeException e) {
            System.out.println(e.outOfBounds());
            System.out.println("Exception in insert method");
        }
        recordSize++;
    }

    @Override
    public void remove(int index) {
        try {
            if (index < 0 || index >= recordSize) throw new OutOfRangeException(index, recordSize);
            System.arraycopy(recordsArray, index + 1, recordsArray, index, recordsArray.length - 1 - index);
        }catch (OutOfRangeException e) {
            System.out.println(e.outOfBounds());
            System.out.println("Exception in remove index method");
        }
    }

    @Override
    public void remove(int fromIndex, int toIndex) {
        try {
            if (fromIndex > toIndex) throw new OutOfRangeException(fromIndex, toIndex);
            System.arraycopy(recordsArray, toIndex, recordsArray, fromIndex, recordsArray.length - toIndex);
            for (int to = 0, i = (recordSize -= toIndex - fromIndex); i < to; i++) recordsArray[i] = null;
        } catch (OutOfRangeException e) {
            System.out.println(e.outOfBounds());
            System.out.println("Exception in remove range method");
        }
    }

    @Override
    public void removeAll() {
        remove(0, size());
    }

    @Override
    public int size() {
        return recordSize;
    }

    @Override
    public Journal filter(String s) {
        Journal journal = new ArrayJournal();
        for (int i = 0; i < recordSize; i++) {
            if (s.equals(recordsArray[i].toString()))
                journal.add(recordsArray[i]);
        }
        return journal;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        Journal journal = new ArrayJournal();
        for (int i = 0; i < recordSize; i++) {
            if (recordsArray[i].getTime().before(toDate) && recordsArray[i].getTime().after(fromDate))
                journal.add(recordsArray[i]);
        }
        return journal;
    }

    @Override
    public void sortByDate() {
        Record[] newRecord = new Record[recordSize];
        System.arraycopy(recordsArray, 0, newRecord, 0, recordSize);
        Arrays.sort(newRecord, Comparator.comparing(Record::getTime));
        System.arraycopy(newRecord, 0, recordsArray, 0, recordSize);
    }

    @Override
    public void sortByImportanceDate() {
        Record[] newRecord = new Record[recordSize];
        System.arraycopy(recordsArray, 0, newRecord, 0, recordSize);
        Arrays.sort(newRecord, Comparator.comparing(Record::getImportance).thenComparing(Record::getTime));
        System.arraycopy(newRecord, 0, recordsArray, 0, recordSize);
    }

    @Override
    public void sortByImportanceSourceDate() {
        Record[] newRecord = new Record[recordSize];
        System.arraycopy(recordsArray, 0, newRecord, 0, recordSize);
        Arrays.sort(newRecord, Comparator.comparing(Record::getImportance).thenComparing(Record::getSource).thenComparing(Record::getTime));
        System.arraycopy(newRecord, 0, recordsArray, 0, recordSize);
    }

    @Override
    public void sortBySourceDate() {
        Record[] newRecord = new Record[recordSize];
        System.arraycopy(recordsArray, 0, newRecord, 0, recordSize);
        Arrays.sort(newRecord, Comparator.comparing(Record::getSource).thenComparing(Record::getTime));
        System.arraycopy(newRecord, 0, recordsArray, 0, recordSize);
    }

    @Override
    public void printRecords() {
        for (int i = 0; i < recordSize; i++) {
            System.out.println(recordsArray[i].toString() + " ");
        }
    }
}