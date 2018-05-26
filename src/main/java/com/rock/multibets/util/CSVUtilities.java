package com.rock.multibets.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.rock.multibets.domain.BrownlowBet;
import com.rock.multibets.domain.Multibet;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sboulden on 5/26/18.
 */
public class CSVUtilities {
    public static void writeMultiBetsToCSV(PrintWriter writer, List<Multibet> bets) {
        for(Multibet bet : bets) {
            LinkedList<String> combos = bet.getBets();
            writer.println("combo: "+bet.getId());
            for(String combo : combos) {
                writer.print(combo);
            }
            writer.println("");
        }
        writer.flush();
        writer.close();
    }

    public static void writeBrownlowBetsToCSV(PrintWriter writer, List<BrownlowBet> bets) {
        for(BrownlowBet bet : bets) {
            LinkedList<String> combos = bet.getBets();
            writer.println("combo: "+bet.getId());
            for(String combo : combos) {
                writer.print(combo);
            }
            writer.println("");
        }
        writer.flush();
        writer.close();
    }
}
