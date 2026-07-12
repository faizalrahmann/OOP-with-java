package com.warungqu.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatUtil {
    private static final DecimalFormat RUPIAH_FORMAT;

    static {
        Locale locale = new Locale("id", "ID");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setCurrencySymbol("Rp ");
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        RUPIAH_FORMAT = new DecimalFormat("¤ #,##0", symbols);
        RUPIAH_FORMAT.setMaximumFractionDigits(0);
        RUPIAH_FORMAT.setMinimumFractionDigits(0);
    }

    public static String formatRupiah(double value) {
        return RUPIAH_FORMAT.format(value);
    }
}
