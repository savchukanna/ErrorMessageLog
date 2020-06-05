package com.company;
import com.company.journals.ArrayJournal;
import com.company.journals.CollectionJournal;
import com.company.record.Importance;
import com.company.record.Record;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

       Record record = new Record(new Date(), Importance.WARNING, "1", "warning");
       Record record2 = new Record(new Date(),Importance.FAILURE_RECOVERY, "2", "failure");

       ArrayJournal arrayJournal = new ArrayJournal();
       CollectionJournal collectionJournal = new CollectionJournal();

       arrayJournal.add(record);
       arrayJournal.add(record2);
       arrayJournal.printRecords();
       arrayJournal.sortByImportanceSourceDate();
       arrayJournal.printRecords();

       System.out.println();

       collectionJournal.add(arrayJournal);
       collectionJournal.printRecords();
       collectionJournal.filter("to");
       collectionJournal.printRecords();
    }
}



