package com.zezoo.accountant;

public class BillListChildItem {

    private int COLUMN, Type, Code;
    private String Name, Date, Time, From, To, Cash, Currency;
    private double Discount, Addition, Total, Final;

    BillListChildItem() {

    }

    BillListChildItem(BillListChildItem blci, String from, String to) {

    }

    BillListChildItem(int COLUMN, int Code, String Name, int Type, String Date, String Time, String From, String To, String Cash, String Currency, double Discount, double Addition, double Total, double Final) {
        this.COLUMN = COLUMN;
        this.Code = Code;
        this.Name = Name;
        this.Type = Type;
        this.Date = Date;
        this.Time = Time;
        this.From = From;
        this.To = To;
        this.Cash = Cash;
        this.Currency = Currency;

        this.Discount = Discount;
        this.Addition = Addition;
        this.Total = Total;
        this.Final = Final;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getAddition() {
        return Addition;
    }

    public void setAddition(double addition) {
        Addition = addition;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getFinal() {
        return Final;
    }

    public void setFinal(double aFinal) {
        Final = aFinal;
    }


    public int getCOLUMN() {
        return COLUMN;
    }

    public void setCOLUMN(int COLUMN) {
        this.COLUMN = COLUMN;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getCash() {
        return Cash;
    }

    public void setCash(String cash) {
        Cash = cash;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    private String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50;
    private double a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50;
    private double o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23, o24, o25, o26, o27, o28, o29, o30, o31, o32, o33, o34, o35, o36, o37, o38, o39, o40, o41, o42, o43, o44, o45, o46, o47, o48, o49, o50;


    void SETTABLE1(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14, String p15, String p16, String p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, String p25, String p26, String p27, String p28, String p29, String p30, String p31, String p32, String p33, String p34, String p35, String p36, String p37, String p38, String p39, String p40, String p41, String p42, String p43, String p44, String p45, String p46, String p47, String p48, String p49, String p50
            , double a1, double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10, double a11, double a12, double a13, double a14, double a15, double a16, double a17, double a18, double a19, double a20, double a21, double a22, double a23, double a24, double a25, double a26, double a27, double a28, double a29, double a30, double a31, double a32, double a33, double a34, double a35, double a36, double a37, double a38, double a39, double a40, double a41, double a42, double a43, double a44, double a45, double a46, double a47, double a48, double a49, double a50
            , double o1, double o2, double o3, double o4, double o5, double o6, double o7, double o8, double o9, double o10, double o11, double o12, double o13, double o14, double o15, double o16, double o17, double o18, double o19, double o20, double o21, double o22, double o23, double o24, double o25, double o26, double o27, double o28, double o29, double o30, double o31, double o32, double o33, double o34, double o35, double o36, double o37, double o38, double o39, double o40, double o41, double o42, double o43, double o44, double o45, double o46, double o47, double o48, double o49, double o50
    ) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
        this.p10 = p10;
        this.p11 = p11;
        this.p12 = p12;
        this.p13 = p13;
        this.p14 = p14;
        this.p15 = p15;
        this.p16 = p16;
        this.p17 = p17;
        this.p18 = p18;
        this.p19 = p19;
        this.p20 = p20;
        this.p21 = p21;
        this.p22 = p22;
        this.p23 = p23;
        this.p24 = p24;
        this.p25 = p25;
        this.p26 = p26;
        this.p27 = p27;
        this.p28 = p28;
        this.p29 = p29;
        this.p30 = p30;
        this.p31 = p31;
        this.p32 = p32;
        this.p33 = p33;
        this.p34 = p34;
        this.p35 = p35;
        this.p36 = p36;
        this.p37 = p37;
        this.p38 = p38;
        this.p39 = p39;
        this.p40 = p40;
        this.p41 = p41;
        this.p42 = p42;
        this.p43 = p43;
        this.p44 = p44;
        this.p45 = p45;
        this.p46 = p46;
        this.p47 = p47;
        this.p48 = p48;
        this.p49 = p49;
        this.p50 = p50;

        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
        this.a7 = a7;
        this.a8 = a8;
        this.a9 = a9;
        this.a10 = a10;
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a14 = a14;
        this.a15 = a15;
        this.a16 = a16;
        this.a17 = a17;
        this.a18 = a18;
        this.a19 = a19;
        this.a20 = a20;
        this.a21 = a21;
        this.a22 = a22;
        this.a23 = a23;
        this.a24 = a24;
        this.a25 = a25;
        this.a26 = a26;
        this.a27 = a27;
        this.a28 = a28;
        this.a29 = a29;
        this.a30 = a30;
        this.a31 = a31;
        this.a32 = a32;
        this.a33 = a33;
        this.a34 = a34;
        this.a35 = a35;
        this.a36 = a36;
        this.a37 = a37;
        this.a38 = a38;
        this.a39 = a39;
        this.a40 = a40;
        this.a41 = a41;
        this.a42 = a42;
        this.a43 = a43;
        this.a44 = a44;
        this.a45 = a45;
        this.a46 = a46;
        this.a47 = a47;
        this.a48 = a48;
        this.a49 = a49;
        this.a50 = a50;

        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.o5 = o5;
        this.o6 = o6;
        this.o7 = o7;
        this.o8 = o8;
        this.o9 = o9;
        this.o10 = o10;
        this.o11 = o11;
        this.o12 = o12;
        this.o13 = o13;
        this.o14 = o14;
        this.o15 = o15;
        this.o16 = o16;
        this.o17 = o17;
        this.o18 = o18;
        this.o19 = o19;
        this.o20 = o20;
        this.o21 = o21;
        this.o22 = o22;
        this.o23 = o23;
        this.o24 = o24;
        this.o25 = o25;
        this.o26 = o26;
        this.o27 = o27;
        this.o28 = o28;
        this.o29 = o29;
        this.o30 = o30;
        this.o31 = o31;
        this.o32 = o32;
        this.o33 = o33;
        this.o34 = o34;
        this.o35 = o35;
        this.o36 = o36;
        this.o37 = o37;
        this.o38 = o38;
        this.o39 = o39;
        this.o40 = o40;
        this.o41 = o41;
        this.o42 = o42;
        this.o43 = o43;
        this.o44 = o44;
        this.o45 = o45;
        this.o46 = o46;
        this.o47 = o47;
        this.o48 = o48;
        this.o49 = o49;
        this.o50 = o50;
    }

    public void setRowByNumber(int num, String product, double amount, double offer, double unit, double total, String state) {
        switch (num) {
            case 1:
                setP1(product);
                setA1(amount);
                setO1(offer);
                setU1(unit);
                setT1(total);
                setS1(state);
                break;
            case 2:
                setP2(product);
                setA2(amount);
                setO2(offer);
                setU2(unit);
                setT2(total);
                setS2(state);
                break;
            case 3:
                setP3(product);
                setA3(amount);
                setO3(offer);
                setU3(unit);
                setT3(total);
                setS3(state);
                break;
            case 4:
                setP4(product);
                setA4(amount);
                setO4(offer);
                setU4(unit);
                setT4(total);
                setS4(state);
                break;
            case 5:
                setP5(product);
                setA5(amount);
                setO5(offer);
                setU5(unit);
                setT5(total);
                setS5(state);
                break;
            case 6:
                setP6(product);
                setA6(amount);
                setO6(offer);
                setU6(unit);
                setT6(total);
                setS6(state);
                break;
            case 7:
                setP7(product);
                setA7(amount);
                setO7(offer);
                setU7(unit);
                setT7(total);
                setS7(state);
                break;
            case 8:
                setP8(product);
                setA8(amount);
                setO8(offer);
                setU8(unit);
                setT8(total);
                setS8(state);
                break;
            case 9:
                setP9(product);
                setA9(amount);
                setO9(offer);
                setU9(unit);
                setT9(total);
                setS9(state);
                break;
            case 10:
                setP10(product);
                setA10(amount);
                setO10(offer);
                setU10(unit);
                setT10(total);
                setS10(state);
                break;
            case 11:
                setP11(product);
                setA11(amount);
                setO11(offer);
                setU11(unit);
                setT11(total);
                setS11(state);
                break;
            case 12:
                setP12(product);
                setA12(amount);
                setO12(offer);
                setU12(unit);
                setT12(total);
                setS12(state);
                break;
            case 13:
                setP13(product);
                setA13(amount);
                setO13(offer);
                setU13(unit);
                setT13(total);
                setS13(state);
                break;
            case 14:
                setP14(product);
                setA14(amount);
                setO14(offer);
                setU14(unit);
                setT14(total);
                setS14(state);
                break;
            case 15:
                setP15(product);
                setA15(amount);
                setO15(offer);
                setU15(unit);
                setT15(total);
                setS15(state);
                break;
            case 16:
                setP16(product);
                setA16(amount);
                setO16(offer);
                setU16(unit);
                setT16(total);
                setS16(state);
                break;
            case 17:
                setP17(product);
                setA17(amount);
                setO17(offer);
                setU17(unit);
                setT17(total);
                setS17(state);
                break;
            case 18:
                setP18(product);
                setA18(amount);
                setO18(offer);
                setU18(unit);
                setT18(total);
                setS18(state);
                break;
            case 19:
                setP19(product);
                setA19(amount);
                setO19(offer);
                setU19(unit);
                setT19(total);
                setS19(state);
                break;
            case 20:
                setP20(product);
                setA20(amount);
                setO20(offer);
                setU20(unit);
                setT20(total);
                setS20(state);
                break;
            case 21:
                setP21(product);
                setA21(amount);
                setO21(offer);
                setU21(unit);
                setT21(total);
                setS21(state);
                break;
            case 22:
                setP22(product);
                setA22(amount);
                setO22(offer);
                setU22(unit);
                setT22(total);
                setS22(state);
                break;
            case 23:
                setP23(product);
                setA23(amount);
                setO23(offer);
                setU23(unit);
                setT23(total);
                setS23(state);
                break;
            case 24:
                setP24(product);
                setA24(amount);
                setO24(offer);
                setU24(unit);
                setT24(total);
                setS24(state);
                break;
            case 25:
                setP25(product);
                setA25(amount);
                setO25(offer);
                setU25(unit);
                setT25(total);
                setS25(state);
                break;
            case 26:
                setP26(product);
                setA26(amount);
                setO26(offer);
                setU26(unit);
                setT26(total);
                setS26(state);
                break;
            case 27:
                setP27(product);
                setA27(amount);
                setO27(offer);
                setU27(unit);
                setT27(total);
                setS27(state);
                break;
            case 28:
                setP28(product);
                setA28(amount);
                setO28(offer);
                setU28(unit);
                setT28(total);
                setS28(state);
                break;
            case 29:
                setP29(product);
                setA29(amount);
                setO29(offer);
                setU29(unit);
                setT29(total);
                setS29(state);
                break;
            case 30:
                setP30(product);
                setA30(amount);
                setO30(offer);
                setU30(unit);
                setT30(total);
                setS30(state);
                break;
            case 31:
                setP31(product);
                setA31(amount);
                setO31(offer);
                setU31(unit);
                setT31(total);
                setS31(state);
                break;
            case 32:
                setP32(product);
                setA32(amount);
                setO32(offer);
                setU32(unit);
                setT32(total);
                setS32(state);
                break;
            case 33:
                setP33(product);
                setA33(amount);
                setO33(offer);
                setU33(unit);
                setT33(total);
                setS33(state);
                break;
            case 34:
                setP34(product);
                setA34(amount);
                setO34(offer);
                setU34(unit);
                setT34(total);
                setS34(state);
                break;
            case 35:
                setP35(product);
                setA35(amount);
                setO35(offer);
                setU35(unit);
                setT35(total);
                setS35(state);
                break;
            case 36:
                setP36(product);
                setA36(amount);
                setO36(offer);
                setU36(unit);
                setT36(total);
                setS36(state);
                break;
            case 37:
                setP37(product);
                setA37(amount);
                setO37(offer);
                setU37(unit);
                setT37(total);
                setS37(state);
                break;
            case 38:
                setP38(product);
                setA38(amount);
                setO38(offer);
                setU38(unit);
                setT38(total);
                setS38(state);
                break;
            case 39:
                setP39(product);
                setA39(amount);
                setO39(offer);
                setU39(unit);
                setT39(total);
                setS39(state);
                break;
            case 40:
                setP40(product);
                setA40(amount);
                setO40(offer);
                setU40(unit);
                setT40(total);
                setS40(state);
                break;
            case 41:
                setP41(product);
                setA41(amount);
                setO41(offer);
                setU41(unit);
                setT41(total);
                setS41(state);
                break;
            case 42:
                setP42(product);
                setA42(amount);
                setO42(offer);
                setU42(unit);
                setT42(total);
                setS42(state);
                break;
            case 43:
                setP43(product);
                setA43(amount);
                setO43(offer);
                setU43(unit);
                setT43(total);
                setS43(state);
                break;
            case 44:
                setP44(product);
                setA44(amount);
                setO44(offer);
                setU44(unit);
                setT44(total);
                setS44(state);
                break;
            case 45:
                setP45(product);
                setA45(amount);
                setO45(offer);
                setU45(unit);
                setT45(total);
                setS45(state);
                break;
            case 46:
                setP46(product);
                setA46(amount);
                setO46(offer);
                setU46(unit);
                setT46(total);
                setS46(state);
                break;
            case 47:
                setP47(product);
                setA47(amount);
                setO47(offer);
                setU47(unit);
                setT47(total);
                setS47(state);
                break;
            case 48:
                setP48(product);
                setA48(amount);
                setO48(offer);
                setU48(unit);
                setT48(total);
                setS48(state);
                break;
            case 49:
                setP49(product);
                setA49(amount);
                setO49(offer);
                setU49(unit);
                setT49(total);
                setS49(state);
                break;
            case 50:
                setP50(product);
                setA50(amount);
                setO50(offer);
                setU50(unit);
                setT50(total);
                setS50(state);
                break;

        }
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP6() {
        return p6;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP7() {
        return p7;
    }

    public void setP7(String p7) {
        this.p7 = p7;
    }

    public String getP8() {
        return p8;
    }

    public void setP8(String p8) {
        this.p8 = p8;
    }

    public String getP9() {
        return p9;
    }

    public void setP9(String p9) {
        this.p9 = p9;
    }

    public String getP10() {
        return p10;
    }

    public void setP10(String p10) {
        this.p10 = p10;
    }

    public String getP11() {
        return p11;
    }

    public void setP11(String p11) {
        this.p11 = p11;
    }

    public String getP12() {
        return p12;
    }

    public void setP12(String p12) {
        this.p12 = p12;
    }

    public String getP13() {
        return p13;
    }

    public void setP13(String p13) {
        this.p13 = p13;
    }

    public String getP14() {
        return p14;
    }

    public void setP14(String p14) {
        this.p14 = p14;
    }

    public String getP15() {
        return p15;
    }

    public void setP15(String p15) {
        this.p15 = p15;
    }

    public String getP16() {
        return p16;
    }

    public void setP16(String p16) {
        this.p16 = p16;
    }

    public String getP17() {
        return p17;
    }

    public void setP17(String p17) {
        this.p17 = p17;
    }

    public String getP18() {
        return p18;
    }

    public void setP18(String p18) {
        this.p18 = p18;
    }

    public String getP19() {
        return p19;
    }

    public void setP19(String p19) {
        this.p19 = p19;
    }

    public String getP20() {
        return p20;
    }

    public void setP20(String p20) {
        this.p20 = p20;
    }

    public String getP21() {
        return p21;
    }

    public void setP21(String p21) {
        this.p21 = p21;
    }

    public String getP22() {
        return p22;
    }

    public void setP22(String p22) {
        this.p22 = p22;
    }

    public String getP23() {
        return p23;
    }

    public void setP23(String p23) {
        this.p23 = p23;
    }

    public String getP24() {
        return p24;
    }

    public void setP24(String p24) {
        this.p24 = p24;
    }

    public String getP25() {
        return p25;
    }

    public void setP25(String p25) {
        this.p25 = p25;
    }

    public String getP26() {
        return p26;
    }

    public void setP26(String p26) {
        this.p26 = p26;
    }

    public String getP27() {
        return p27;
    }

    public void setP27(String p27) {
        this.p27 = p27;
    }

    public String getP28() {
        return p28;
    }

    public void setP28(String p28) {
        this.p28 = p28;
    }

    public String getP29() {
        return p29;
    }

    public void setP29(String p29) {
        this.p29 = p29;
    }

    public String getP30() {
        return p30;
    }

    public void setP30(String p30) {
        this.p30 = p30;
    }

    public String getP31() {
        return p31;
    }

    public void setP31(String p31) {
        this.p31 = p31;
    }

    public String getP32() {
        return p32;
    }

    public void setP32(String p32) {
        this.p32 = p32;
    }

    public String getP33() {
        return p33;
    }

    public void setP33(String p33) {
        this.p33 = p33;
    }

    public String getP34() {
        return p34;
    }

    public void setP34(String p34) {
        this.p34 = p34;
    }

    public String getP35() {
        return p35;
    }

    public void setP35(String p35) {
        this.p35 = p35;
    }

    public String getP36() {
        return p36;
    }

    public void setP36(String p36) {
        this.p36 = p36;
    }

    public String getP37() {
        return p37;
    }

    public void setP37(String p37) {
        this.p37 = p37;
    }

    public String getP38() {
        return p38;
    }

    public void setP38(String p38) {
        this.p38 = p38;
    }

    public String getP39() {
        return p39;
    }

    public void setP39(String p39) {
        this.p39 = p39;
    }

    public String getP40() {
        return p40;
    }

    public void setP40(String p40) {
        this.p40 = p40;
    }

    public String getP41() {
        return p41;
    }

    public void setP41(String p41) {
        this.p41 = p41;
    }

    public String getP42() {
        return p42;
    }

    public void setP42(String p42) {
        this.p42 = p42;
    }

    public String getP43() {
        return p43;
    }

    public void setP43(String p43) {
        this.p43 = p43;
    }

    public String getP44() {
        return p44;
    }

    public void setP44(String p44) {
        this.p44 = p44;
    }

    public String getP45() {
        return p45;
    }

    public void setP45(String p45) {
        this.p45 = p45;
    }

    public String getP46() {
        return p46;
    }

    public void setP46(String p46) {
        this.p46 = p46;
    }

    public String getP47() {
        return p47;
    }

    public void setP47(String p47) {
        this.p47 = p47;
    }

    public String getP48() {
        return p48;
    }

    public void setP48(String p48) {
        this.p48 = p48;
    }

    public String getP49() {
        return p49;
    }

    public void setP49(String p49) {
        this.p49 = p49;
    }

    public String getP50() {
        return p50;
    }

    public void setP50(String p50) {
        this.p50 = p50;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double getA4() {
        return a4;
    }

    public void setA4(double a4) {
        this.a4 = a4;
    }

    public double getA5() {
        return a5;
    }

    public void setA5(double a5) {
        this.a5 = a5;
    }

    public double getA6() {
        return a6;
    }

    public void setA6(double a6) {
        this.a6 = a6;
    }

    public double getA7() {
        return a7;
    }

    public void setA7(double a7) {
        this.a7 = a7;
    }

    public double getA8() {
        return a8;
    }

    public void setA8(double a8) {
        this.a8 = a8;
    }

    public double getA9() {
        return a9;
    }

    public void setA9(double a9) {
        this.a9 = a9;
    }

    public double getA10() {
        return a10;
    }

    public void setA10(double a10) {
        this.a10 = a10;
    }

    public double getA11() {
        return a11;
    }

    public void setA11(double a11) {
        this.a11 = a11;
    }

    public double getA12() {
        return a12;
    }

    public void setA12(double a12) {
        this.a12 = a12;
    }

    public double getA13() {
        return a13;
    }

    public void setA13(double a13) {
        this.a13 = a13;
    }

    public double getA14() {
        return a14;
    }

    public void setA14(double a14) {
        this.a14 = a14;
    }

    public double getA15() {
        return a15;
    }

    public void setA15(double a15) {
        this.a15 = a15;
    }

    public double getA16() {
        return a16;
    }

    public void setA16(double a16) {
        this.a16 = a16;
    }

    public double getA17() {
        return a17;
    }

    public void setA17(double a17) {
        this.a17 = a17;
    }

    public double getA18() {
        return a18;
    }

    public void setA18(double a18) {
        this.a18 = a18;
    }

    public double getA19() {
        return a19;
    }

    public void setA19(double a19) {
        this.a19 = a19;
    }

    public double getA20() {
        return a20;
    }

    public void setA20(double a20) {
        this.a20 = a20;
    }

    public double getA21() {
        return a21;
    }

    public void setA21(double a21) {
        this.a21 = a21;
    }

    public double getA22() {
        return a22;
    }

    public void setA22(double a22) {
        this.a22 = a22;
    }

    public double getA23() {
        return a23;
    }

    public void setA23(double a23) {
        this.a23 = a23;
    }

    public double getA24() {
        return a24;
    }

    public void setA24(double a24) {
        this.a24 = a24;
    }

    public double getA25() {
        return a25;
    }

    public void setA25(double a25) {
        this.a25 = a25;
    }

    public double getA26() {
        return a26;
    }

    public void setA26(double a26) {
        this.a26 = a26;
    }

    public double getA27() {
        return a27;
    }

    public void setA27(double a27) {
        this.a27 = a27;
    }

    public double getA28() {
        return a28;
    }

    public void setA28(double a28) {
        this.a28 = a28;
    }

    public double getA29() {
        return a29;
    }

    public void setA29(double a29) {
        this.a29 = a29;
    }

    public double getA30() {
        return a30;
    }

    public void setA30(double a30) {
        this.a30 = a30;
    }

    public double getA31() {
        return a31;
    }

    public void setA31(double a31) {
        this.a31 = a31;
    }

    public double getA32() {
        return a32;
    }

    public void setA32(double a32) {
        this.a32 = a32;
    }

    public double getA33() {
        return a33;
    }

    public void setA33(double a33) {
        this.a33 = a33;
    }

    public double getA34() {
        return a34;
    }

    public void setA34(double a34) {
        this.a34 = a34;
    }

    public double getA35() {
        return a35;
    }

    public void setA35(double a35) {
        this.a35 = a35;
    }

    public double getA36() {
        return a36;
    }

    public void setA36(double a36) {
        this.a36 = a36;
    }

    public double getA37() {
        return a37;
    }

    public void setA37(double a37) {
        this.a37 = a37;
    }

    public double getA38() {
        return a38;
    }

    public void setA38(double a38) {
        this.a38 = a38;
    }

    public double getA39() {
        return a39;
    }

    public void setA39(double a39) {
        this.a39 = a39;
    }

    public double getA40() {
        return a40;
    }

    public void setA40(double a40) {
        this.a40 = a40;
    }

    public double getA41() {
        return a41;
    }

    public void setA41(double a41) {
        this.a41 = a41;
    }

    public double getA42() {
        return a42;
    }

    public void setA42(double a42) {
        this.a42 = a42;
    }

    public double getA43() {
        return a43;
    }

    public void setA43(double a43) {
        this.a43 = a43;
    }

    public double getA44() {
        return a44;
    }

    public void setA44(double a44) {
        this.a44 = a44;
    }

    public double getA45() {
        return a45;
    }

    public void setA45(double a45) {
        this.a45 = a45;
    }

    public double getA46() {
        return a46;
    }

    public void setA46(double a46) {
        this.a46 = a46;
    }

    public double getA47() {
        return a47;
    }

    public void setA47(double a47) {
        this.a47 = a47;
    }

    public double getA48() {
        return a48;
    }

    public void setA48(double a48) {
        this.a48 = a48;
    }

    public double getA49() {
        return a49;
    }

    public void setA49(double a49) {
        this.a49 = a49;
    }

    public double getA50() {
        return a50;
    }

    public void setA50(double a50) {
        this.a50 = a50;
    }

    public double getO1() {
        return o1;
    }

    public void setO1(double o1) {
        this.o1 = o1;
    }

    public double getO2() {
        return o2;
    }

    public void setO2(double o2) {
        this.o2 = o2;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getO4() {
        return o4;
    }

    public void setO4(double o4) {
        this.o4 = o4;
    }

    public double getO5() {
        return o5;
    }

    public void setO5(double o5) {
        this.o5 = o5;
    }

    public double getO6() {
        return o6;
    }

    public void setO6(double o6) {
        this.o6 = o6;
    }

    public double getO7() {
        return o7;
    }

    public void setO7(double o7) {
        this.o7 = o7;
    }

    public double getO8() {
        return o8;
    }

    public void setO8(double o8) {
        this.o8 = o8;
    }

    public double getO9() {
        return o9;
    }

    public void setO9(double o9) {
        this.o9 = o9;
    }

    public double getO10() {
        return o10;
    }

    public void setO10(double o10) {
        this.o10 = o10;
    }

    public double getO11() {
        return o11;
    }

    public void setO11(double o11) {
        this.o11 = o11;
    }

    public double getO12() {
        return o12;
    }

    public void setO12(double o12) {
        this.o12 = o12;
    }

    public double getO13() {
        return o13;
    }

    public void setO13(double o13) {
        this.o13 = o13;
    }

    public double getO14() {
        return o14;
    }

    public void setO14(double o14) {
        this.o14 = o14;
    }

    public double getO15() {
        return o15;
    }

    public void setO15(double o15) {
        this.o15 = o15;
    }

    public double getO16() {
        return o16;
    }

    public void setO16(double o16) {
        this.o16 = o16;
    }

    public double getO17() {
        return o17;
    }

    public void setO17(double o17) {
        this.o17 = o17;
    }

    public double getO18() {
        return o18;
    }

    public void setO18(double o18) {
        this.o18 = o18;
    }

    public double getO19() {
        return o19;
    }

    public void setO19(double o19) {
        this.o19 = o19;
    }

    public double getO20() {
        return o20;
    }

    public void setO20(double o20) {
        this.o20 = o20;
    }

    public double getO21() {
        return o21;
    }

    public void setO21(double o21) {
        this.o21 = o21;
    }

    public double getO22() {
        return o22;
    }

    public void setO22(double o22) {
        this.o22 = o22;
    }

    public double getO23() {
        return o23;
    }

    public void setO23(double o23) {
        this.o23 = o23;
    }

    public double getO24() {
        return o24;
    }

    public void setO24(double o24) {
        this.o24 = o24;
    }

    public double getO25() {
        return o25;
    }

    public void setO25(double o25) {
        this.o25 = o25;
    }

    public double getO26() {
        return o26;
    }

    public void setO26(double o26) {
        this.o26 = o26;
    }

    public double getO27() {
        return o27;
    }

    public void setO27(double o27) {
        this.o27 = o27;
    }

    public double getO28() {
        return o28;
    }

    public void setO28(double o28) {
        this.o28 = o28;
    }

    public double getO29() {
        return o29;
    }

    public void setO29(double o29) {
        this.o29 = o29;
    }

    public double getO30() {
        return o30;
    }

    public void setO30(double o30) {
        this.o30 = o30;
    }

    public double getO31() {
        return o31;
    }

    public void setO31(double o31) {
        this.o31 = o31;
    }

    public double getO32() {
        return o32;
    }

    public void setO32(double o32) {
        this.o32 = o32;
    }

    public double getO33() {
        return o33;
    }

    public void setO33(double o33) {
        this.o33 = o33;
    }

    public double getO34() {
        return o34;
    }

    public void setO34(double o34) {
        this.o34 = o34;
    }

    public double getO35() {
        return o35;
    }

    public void setO35(double o35) {
        this.o35 = o35;
    }

    public double getO36() {
        return o36;
    }

    public void setO36(double o36) {
        this.o36 = o36;
    }

    public double getO37() {
        return o37;
    }

    public void setO37(double o37) {
        this.o37 = o37;
    }

    public double getO38() {
        return o38;
    }

    public void setO38(double o38) {
        this.o38 = o38;
    }

    public double getO39() {
        return o39;
    }

    public void setO39(double o39) {
        this.o39 = o39;
    }

    public double getO40() {
        return o40;
    }

    public void setO40(double o40) {
        this.o40 = o40;
    }

    public double getO41() {
        return o41;
    }

    public void setO41(double o41) {
        this.o41 = o41;
    }

    public double getO42() {
        return o42;
    }

    public void setO42(double o42) {
        this.o42 = o42;
    }

    public double getO43() {
        return o43;
    }

    public void setO43(double o43) {
        this.o43 = o43;
    }

    public double getO44() {
        return o44;
    }

    public void setO44(double o44) {
        this.o44 = o44;
    }

    public double getO45() {
        return o45;
    }

    public void setO45(double o45) {
        this.o45 = o45;
    }

    public double getO46() {
        return o46;
    }

    public void setO46(double o46) {
        this.o46 = o46;
    }

    public double getO47() {
        return o47;
    }

    public void setO47(double o47) {
        this.o47 = o47;
    }

    public double getO48() {
        return o48;
    }

    public void setO48(double o48) {
        this.o48 = o48;
    }

    public double getO49() {
        return o49;
    }

    public void setO49(double o49) {
        this.o49 = o49;
    }

    public double getO50() {
        return o50;
    }

    public void setO50(double o50) {
        this.o50 = o50;
    }

    private double u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21, u22, u23, u24, u25, u26, u27, u28, u29, u30, u31, u32, u33, u34, u35, u36, u37, u38, u39, u40, u41, u42, u43, u44, u45, u46, u47, u48, u49, u50;
    private double t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43, t44, t45, t46, t47, t48, t49, t50;
    private String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41, s42, s43, s44, s45, s46, s47, s48, s49, s50;


    void SETTABLE2(double u1, double u2, double u3, double u4, double u5, double u6, double u7, double u8, double u9, double u10, double u11, double u12, double u13, double u14, double u15, double u16, double u17, double u18, double u19, double u20, double u21, double u22, double u23, double u24, double u25, double u26, double u27, double u28, double u29, double u30, double u31, double u32, double u33, double u34, double u35, double u36, double u37, double u38, double u39, double u40, double u41, double u42, double u43, double u44, double u45, double u46, double u47, double u48, double u49, double u50
            , double t1, double t2, double t3, double t4, double t5, double t6, double t7, double t8, double t9, double t10, double t11, double t12, double t13, double t14, double t15, double t16, double t17, double t18, double t19, double t20, double t21, double t22, double t23, double t24, double t25, double t26, double t27, double t28, double t29, double t30, double t31, double t32, double t33, double t34, double t35, double t36, double t37, double t38, double t39, double t40, double t41, double t42, double t43, double t44, double t45, double t46, double t47, double t48, double t49, double t50
            , String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s31, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s13, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46, String s47, String s48, String s49, String s50) {
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
        this.u4 = u4;
        this.u5 = u5;
        this.u6 = u6;
        this.u7 = u7;
        this.u8 = u8;
        this.u9 = u9;
        this.u10 = u10;
        this.u11 = u11;
        this.u12 = u12;
        this.u13 = u13;
        this.u14 = u14;
        this.u15 = u15;
        this.u16 = u16;
        this.u17 = u17;
        this.u18 = u18;
        this.u19 = u19;
        this.u20 = u20;
        this.u21 = u21;
        this.u22 = u22;
        this.u23 = u23;
        this.u24 = u24;
        this.u25 = u25;
        this.u26 = u26;
        this.u27 = u27;
        this.u28 = u28;
        this.u29 = u29;
        this.u30 = u30;
        this.u31 = u31;
        this.u32 = u32;
        this.u33 = u33;
        this.u34 = u34;
        this.u35 = u35;
        this.u36 = u36;
        this.u37 = u37;
        this.u38 = u38;
        this.u39 = u39;
        this.u40 = u40;
        this.u41 = u41;
        this.u42 = u42;
        this.u43 = u43;
        this.u44 = u44;
        this.u45 = u45;
        this.u46 = u46;
        this.u47 = u47;
        this.u48 = u48;
        this.u49 = u49;
        this.u50 = u50;

        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
        this.t7 = t7;
        this.t8 = t8;
        this.t9 = t9;
        this.t10 = t10;
        this.t11 = t11;
        this.t12 = t12;
        this.t13 = t13;
        this.t14 = t14;
        this.t15 = t15;
        this.t16 = t16;
        this.t17 = t17;
        this.t18 = t18;
        this.t19 = t19;
        this.t20 = t20;
        this.t21 = t21;
        this.t22 = t22;
        this.t23 = t23;
        this.t24 = t24;
        this.t25 = t25;
        this.t26 = t26;
        this.t27 = t27;
        this.t28 = t28;
        this.t29 = t29;
        this.t30 = t30;
        this.t31 = t31;
        this.t32 = t32;
        this.t33 = t33;
        this.t34 = t34;
        this.t35 = t35;
        this.t36 = t36;
        this.t37 = t37;
        this.t38 = t38;
        this.t39 = t39;
        this.t40 = t40;
        this.t41 = t41;
        this.t42 = t42;
        this.t43 = t43;
        this.t44 = t44;
        this.t45 = t45;
        this.t46 = t46;
        this.t47 = t47;
        this.t48 = t48;
        this.t49 = t49;
        this.t50 = t50;

        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.s10 = s10;
        this.s11 = s11;
        this.s12 = s12;
        this.s13 = s13;
        this.s14 = s14;
        this.s15 = s15;
        this.s16 = s16;
        this.s17 = s17;
        this.s18 = s18;
        this.s19 = s19;
        this.s20 = s20;
        this.s21 = s21;
        this.s22 = s22;
        this.s23 = s23;
        this.s24 = s24;
        this.s25 = s25;
        this.s26 = s26;
        this.s27 = s27;
        this.s28 = s28;
        this.s29 = s29;
        this.s30 = s30;
        this.s31 = s31;
        this.s32 = s32;
        this.s33 = s33;
        this.s34 = s34;
        this.s35 = s35;
        this.s36 = s36;
        this.s37 = s37;
        this.s38 = s38;
        this.s39 = s39;
        this.s40 = s40;
        this.s41 = s41;
        this.s42 = s42;
        this.s43 = s43;
        this.s44 = s44;
        this.s45 = s45;
        this.s46 = s46;
        this.s47 = s47;
        this.s48 = s48;
        this.s49 = s49;
        this.s50 = s50;
    }

    public double getU1() {
        return u1;
    }

    public void setU1(double u1) {
        this.u1 = u1;
    }

    public double getU2() {
        return u2;
    }

    public void setU2(double u2) {
        this.u2 = u2;
    }

    public double getU3() {
        return u3;
    }

    public void setU3(double u3) {
        this.u3 = u3;
    }

    public double getU4() {
        return u4;
    }

    public void setU4(double u4) {
        this.u4 = u4;
    }

    public double getU5() {
        return u5;
    }

    public void setU5(double u5) {
        this.u5 = u5;
    }

    public double getU6() {
        return u6;
    }

    public void setU6(double u6) {
        this.u6 = u6;
    }

    public double getU7() {
        return u7;
    }

    public void setU7(double u7) {
        this.u7 = u7;
    }

    public double getU8() {
        return u8;
    }

    public void setU8(double u8) {
        this.u8 = u8;
    }

    public double getU9() {
        return u9;
    }

    public void setU9(double u9) {
        this.u9 = u9;
    }

    public double getU10() {
        return u10;
    }

    public void setU10(double u10) {
        this.u10 = u10;
    }

    public double getU11() {
        return u11;
    }

    public void setU11(double u11) {
        this.u11 = u11;
    }

    public double getU12() {
        return u12;
    }

    public void setU12(double u12) {
        this.u12 = u12;
    }

    public double getU13() {
        return u13;
    }

    public void setU13(double u13) {
        this.u13 = u13;
    }

    public double getU14() {
        return u14;
    }

    public void setU14(double u14) {
        this.u14 = u14;
    }

    public double getU15() {
        return u15;
    }

    public void setU15(double u15) {
        this.u15 = u15;
    }

    public double getU16() {
        return u16;
    }

    public void setU16(double u16) {
        this.u16 = u16;
    }

    public double getU17() {
        return u17;
    }

    public void setU17(double u17) {
        this.u17 = u17;
    }

    public double getU18() {
        return u18;
    }

    public void setU18(double u18) {
        this.u18 = u18;
    }

    public double getU19() {
        return u19;
    }

    public void setU19(double u19) {
        this.u19 = u19;
    }

    public double getU20() {
        return u20;
    }

    public void setU20(double u20) {
        this.u20 = u20;
    }

    public double getU21() {
        return u21;
    }

    public void setU21(double u21) {
        this.u21 = u21;
    }

    public double getU22() {
        return u22;
    }

    public void setU22(double u22) {
        this.u22 = u22;
    }

    public double getU23() {
        return u23;
    }

    public void setU23(double u23) {
        this.u23 = u23;
    }

    public double getU24() {
        return u24;
    }

    public void setU24(double u24) {
        this.u24 = u24;
    }

    public double getU25() {
        return u25;
    }

    public void setU25(double u25) {
        this.u25 = u25;
    }

    public double getU26() {
        return u26;
    }

    public void setU26(double u26) {
        this.u26 = u26;
    }

    public double getU27() {
        return u27;
    }

    public void setU27(double u27) {
        this.u27 = u27;
    }

    public double getU28() {
        return u28;
    }

    public void setU28(double u28) {
        this.u28 = u28;
    }

    public double getU29() {
        return u29;
    }

    public void setU29(double u29) {
        this.u29 = u29;
    }

    public double getU30() {
        return u30;
    }

    public void setU30(double u30) {
        this.u30 = u30;
    }

    public double getU31() {
        return u31;
    }

    public void setU31(double u31) {
        this.u31 = u31;
    }

    public double getU32() {
        return u32;
    }

    public void setU32(double u32) {
        this.u32 = u32;
    }

    public double getU33() {
        return u33;
    }

    public void setU33(double u33) {
        this.u33 = u33;
    }

    public double getU34() {
        return u34;
    }

    public void setU34(double u34) {
        this.u34 = u34;
    }

    public double getU35() {
        return u35;
    }

    public void setU35(double u35) {
        this.u35 = u35;
    }

    public double getU36() {
        return u36;
    }

    public void setU36(double u36) {
        this.u36 = u36;
    }

    public double getU37() {
        return u37;
    }

    public void setU37(double u37) {
        this.u37 = u37;
    }

    public double getU38() {
        return u38;
    }

    public void setU38(double u38) {
        this.u38 = u38;
    }

    public double getU39() {
        return u39;
    }

    public void setU39(double u39) {
        this.u39 = u39;
    }

    public double getU40() {
        return u40;
    }

    public void setU40(double u40) {
        this.u40 = u40;
    }

    public double getU41() {
        return u41;
    }

    public void setU41(double u41) {
        this.u41 = u41;
    }

    public double getU42() {
        return u42;
    }

    public void setU42(double u42) {
        this.u42 = u42;
    }

    public double getU43() {
        return u43;
    }

    public void setU43(double u43) {
        this.u43 = u43;
    }

    public double getU44() {
        return u44;
    }

    public void setU44(double u44) {
        this.u44 = u44;
    }

    public double getU45() {
        return u45;
    }

    public void setU45(double u45) {
        this.u45 = u45;
    }

    public double getU46() {
        return u46;
    }

    public void setU46(double u46) {
        this.u46 = u46;
    }

    public double getU47() {
        return u47;
    }

    public void setU47(double u47) {
        this.u47 = u47;
    }

    public double getU48() {
        return u48;
    }

    public void setU48(double u48) {
        this.u48 = u48;
    }

    public double getU49() {
        return u49;
    }

    public void setU49(double u49) {
        this.u49 = u49;
    }

    public double getU50() {
        return u50;
    }

    public void setU50(double u50) {
        this.u50 = u50;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public double getT3() {
        return t3;
    }

    public void setT3(double t3) {
        this.t3 = t3;
    }

    public double getT4() {
        return t4;
    }

    public void setT4(double t4) {
        this.t4 = t4;
    }

    public double getT5() {
        return t5;
    }

    public void setT5(double t5) {
        this.t5 = t5;
    }

    public double getT6() {
        return t6;
    }

    public void setT6(double t6) {
        this.t6 = t6;
    }

    public double getT7() {
        return t7;
    }

    public void setT7(double t7) {
        this.t7 = t7;
    }

    public double getT8() {
        return t8;
    }

    public void setT8(double t8) {
        this.t8 = t8;
    }

    public double getT9() {
        return t9;
    }

    public void setT9(double t9) {
        this.t9 = t9;
    }

    public double getT10() {
        return t10;
    }

    public void setT10(double t10) {
        this.t10 = t10;
    }

    public double getT11() {
        return t11;
    }

    public void setT11(double t11) {
        this.t11 = t11;
    }

    public double getT12() {
        return t12;
    }

    public void setT12(double t12) {
        this.t12 = t12;
    }

    public double getT13() {
        return t13;
    }

    public void setT13(double t13) {
        this.t13 = t13;
    }

    public double getT14() {
        return t14;
    }

    public void setT14(double t14) {
        this.t14 = t14;
    }

    public double getT15() {
        return t15;
    }

    public void setT15(double t15) {
        this.t15 = t15;
    }

    public double getT16() {
        return t16;
    }

    public void setT16(double t16) {
        this.t16 = t16;
    }

    public double getT17() {
        return t17;
    }

    public void setT17(double t17) {
        this.t17 = t17;
    }

    public double getT18() {
        return t18;
    }

    public void setT18(double t18) {
        this.t18 = t18;
    }

    public double getT19() {
        return t19;
    }

    public void setT19(double t19) {
        this.t19 = t19;
    }

    public double getT20() {
        return t20;
    }

    public void setT20(double t20) {
        this.t20 = t20;
    }

    public double getT21() {
        return t21;
    }

    public void setT21(double t21) {
        this.t21 = t21;
    }

    public double getT22() {
        return t22;
    }

    public void setT22(double t22) {
        this.t22 = t22;
    }

    public double getT23() {
        return t23;
    }

    public void setT23(double t23) {
        this.t23 = t23;
    }

    public double getT24() {
        return t24;
    }

    public void setT24(double t24) {
        this.t24 = t24;
    }

    public double getT25() {
        return t25;
    }

    public void setT25(double t25) {
        this.t25 = t25;
    }

    public double getT26() {
        return t26;
    }

    public void setT26(double t26) {
        this.t26 = t26;
    }

    public double getT27() {
        return t27;
    }

    public void setT27(double t27) {
        this.t27 = t27;
    }

    public double getT28() {
        return t28;
    }

    public void setT28(double t28) {
        this.t28 = t28;
    }

    public double getT29() {
        return t29;
    }

    public void setT29(double t29) {
        this.t29 = t29;
    }

    public double getT30() {
        return t30;
    }

    public void setT30(double t30) {
        this.t30 = t30;
    }

    public double getT31() {
        return t31;
    }

    public void setT31(double t31) {
        this.t31 = t31;
    }

    public double getT32() {
        return t32;
    }

    public void setT32(double t32) {
        this.t32 = t32;
    }

    public double getT33() {
        return t33;
    }

    public void setT33(double t33) {
        this.t33 = t33;
    }

    public double getT34() {
        return t34;
    }

    public void setT34(double t34) {
        this.t34 = t34;
    }

    public double getT35() {
        return t35;
    }

    public void setT35(double t35) {
        this.t35 = t35;
    }

    public double getT36() {
        return t36;
    }

    public void setT36(double t36) {
        this.t36 = t36;
    }

    public double getT37() {
        return t37;
    }

    public void setT37(double t37) {
        this.t37 = t37;
    }

    public double getT38() {
        return t38;
    }

    public void setT38(double t38) {
        this.t38 = t38;
    }

    public double getT39() {
        return t39;
    }

    public void setT39(double t39) {
        this.t39 = t39;
    }

    public double getT40() {
        return t40;
    }

    public void setT40(double t40) {
        this.t40 = t40;
    }

    public double getT41() {
        return t41;
    }

    public void setT41(double t41) {
        this.t41 = t41;
    }

    public double getT42() {
        return t42;
    }

    public void setT42(double t42) {
        this.t42 = t42;
    }

    public double getT43() {
        return t43;
    }

    public void setT43(double t43) {
        this.t43 = t43;
    }

    public double getT44() {
        return t44;
    }

    public void setT44(double t44) {
        this.t44 = t44;
    }

    public double getT45() {
        return t45;
    }

    public void setT45(double t45) {
        this.t45 = t45;
    }

    public double getT46() {
        return t46;
    }

    public void setT46(double t46) {
        this.t46 = t46;
    }

    public double getT47() {
        return t47;
    }

    public void setT47(double t47) {
        this.t47 = t47;
    }

    public double getT48() {
        return t48;
    }

    public void setT48(double t48) {
        this.t48 = t48;
    }

    public double getT49() {
        return t49;
    }

    public void setT49(double t49) {
        this.t49 = t49;
    }

    public double getT50() {
        return t50;
    }

    public void setT50(double t50) {
        this.t50 = t50;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }

    public String getS10() {
        return s10;
    }

    public void setS10(String s10) {
        this.s10 = s10;
    }

    public String getS11() {
        return s11;
    }

    public void setS11(String s11) {
        this.s11 = s11;
    }

    public String getS12() {
        return s12;
    }

    public void setS12(String s12) {
        this.s12 = s12;
    }

    public String getS13() {
        return s13;
    }

    public void setS13(String s13) {
        this.s13 = s13;
    }

    public String getS14() {
        return s14;
    }

    public void setS14(String s14) {
        this.s14 = s14;
    }

    public String getS15() {
        return s15;
    }

    public void setS15(String s15) {
        this.s15 = s15;
    }

    public String getS16() {
        return s16;
    }

    public void setS16(String s16) {
        this.s16 = s16;
    }

    public String getS17() {
        return s17;
    }

    public void setS17(String s17) {
        this.s17 = s17;
    }

    public String getS18() {
        return s18;
    }

    public void setS18(String s18) {
        this.s18 = s18;
    }

    public String getS19() {
        return s19;
    }

    public void setS19(String s19) {
        this.s19 = s19;
    }

    public String getS20() {
        return s20;
    }

    public void setS20(String s20) {
        this.s20 = s20;
    }

    public String getS21() {
        return s21;
    }

    public void setS21(String s21) {
        this.s21 = s21;
    }

    public String getS22() {
        return s22;
    }

    public void setS22(String s22) {
        this.s22 = s22;
    }

    public String getS23() {
        return s23;
    }

    public void setS23(String s23) {
        this.s23 = s23;
    }

    public String getS24() {
        return s24;
    }

    public void setS24(String s24) {
        this.s24 = s24;
    }

    public String getS25() {
        return s25;
    }

    public void setS25(String s25) {
        this.s25 = s25;
    }

    public String getS26() {
        return s26;
    }

    public void setS26(String s26) {
        this.s26 = s26;
    }

    public String getS27() {
        return s27;
    }

    public void setS27(String s27) {
        this.s27 = s27;
    }

    public String getS28() {
        return s28;
    }

    public void setS28(String s28) {
        this.s28 = s28;
    }

    public String getS29() {
        return s29;
    }

    public void setS29(String s29) {
        this.s29 = s29;
    }

    public String getS30() {
        return s30;
    }

    public void setS30(String s30) {
        this.s30 = s30;
    }

    public String getS31() {
        return s31;
    }

    public void setS31(String s31) {
        this.s31 = s31;
    }

    public String getS32() {
        return s32;
    }

    public void setS32(String s32) {
        this.s32 = s32;
    }

    public String getS33() {
        return s33;
    }

    public void setS33(String s33) {
        this.s33 = s33;
    }

    public String getS34() {
        return s34;
    }

    public void setS34(String s34) {
        this.s34 = s34;
    }

    public String getS35() {
        return s35;
    }

    public void setS35(String s35) {
        this.s35 = s35;
    }

    public String getS36() {
        return s36;
    }

    public void setS36(String s36) {
        this.s36 = s36;
    }

    public String getS37() {
        return s37;
    }

    public void setS37(String s37) {
        this.s37 = s37;
    }

    public String getS38() {
        return s38;
    }

    public void setS38(String s38) {
        this.s38 = s38;
    }

    public String getS39() {
        return s39;
    }

    public void setS39(String s39) {
        this.s39 = s39;
    }

    public String getS40() {
        return s40;
    }

    public void setS40(String s40) {
        this.s40 = s40;
    }

    public String getS41() {
        return s41;
    }

    public void setS41(String s41) {
        this.s41 = s41;
    }

    public String getS42() {
        return s42;
    }

    public void setS42(String s42) {
        this.s42 = s42;
    }

    public String getS43() {
        return s43;
    }

    public void setS43(String s43) {
        this.s43 = s43;
    }

    public String getS44() {
        return s44;
    }

    public void setS44(String s44) {
        this.s44 = s44;
    }

    public String getS45() {
        return s45;
    }

    public void setS45(String s45) {
        this.s45 = s45;
    }

    public String getS46() {
        return s46;
    }

    public void setS46(String s46) {
        this.s46 = s46;
    }

    public String getS47() {
        return s47;
    }

    public void setS47(String s47) {
        this.s47 = s47;
    }

    public String getS48() {
        return s48;
    }

    public void setS48(String s48) {
        this.s48 = s48;
    }

    public String getS49() {
        return s49;
    }

    public void setS49(String s49) {
        this.s49 = s49;
    }

    public String getS50() {
        return s50;
    }

    public void setS50(String s50) {
        this.s50 = s50;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
