package mx.com.ananda.cronos.juno.model.dto.sap;

import lombok.Data;

@Data
public class ItemSAP {
    private Long idItem;
    private String itemCode;
    private String itemName;
    public String vatLiable;
    public String taxType;
    private String ncmCode;
    public String indirectTax;
    private char itemType;
    private String mainsupplier;
    private int itemsGroupCode;
    private Double salesUnitWeight;
    private int salesWeightUnit;
    private double inventoryWeight;
    private double saleItemsPerUnit;
    private String planningSystem;
    private String procurementMethod;
    private String barCode;
    private String uCLASIFSTORE;
    private String uCodigo;
    private String salesUnit;
    private double sqlesQtyPerPackUnit;
    private String inventoryUOM;
    private String purchaeUnit;
    private String purchasePackagingUnit;
    private String salesPackagingUnit;
    private String sww;

    /* +++ PROPIEDADES  +++ */
    private String properties1;     //Linea
    private String properties2;     //Mixto
    private String properties3;     //Casa
    private String properties4;     //ProductoN
    private String properties5;     //LAColors
    private String properties6;     //RubyRose
    private String properties7;     //IMNatural
    private String properties8;     //peaceGlos
    private String properties9;     //Curtis
    private String properties10;    //Moda
    private String properties11;    //Temporada
    private String properties12;    //AretesP
    private String properties13;    //AnilloP
    private String properties14;    //Collar
    private String properties15;    //CabelloApple
    private String properties16;    //AccesoriosApple
    private String properties17;    //PestanasApple
    private String properties18;    //AplicadoresApple
    private String properties19;    //DepiladoresApple
    private String properties20;    //LapizAND
    private String properties21;    //CosmeticoMega
    private String properties22;    //DonasValerinas
    private String properties23;    //BrosPasCucas
    private String properties24;    //PinzasDiademas
    private String properties25;    //LigasColetero
    private String properties26;    //Naturone
    private String properties27;    //Prosa41
    private String properties28;    //Postizas
    private String properties29;    //Hogar
    private String properties30;    //Cocina
    private String properties31;    //Juguetes
    private String properties32;    //Escritura
    private String properties33;    //CortaPegaaquesRegalo
    private String properties35;    //Ultramo
    private String properties36;    //Remate
    private String properties37;    //Importacion
    private String properties38;    //TempSep
    private String properties39;    //TempHal
    private String properties40;    //TempNav
    private String properties41;    //Fiesta
    private String properties42;    //CajaCerrada
    private String properties43;    //Nails
    private String properties44;    //AccesoriosMegaline
    private String properties45;    //CabelloMegaline
    private String properties46;    //AccesoriosAND
    private String properties47;    //CabelloAND
    private String properties48;    //PestanaAND
    private String properties49;    //DepiladoresAND
    private String properties50;    //AplicadoresAND
    private String properties57;    //AccesorioBelleza
    private String properties58;    //Bissu
    private String properties59;    //Maravilla
    private String properties60;    //ByApple
    private String properties61;    //Saniye
    private String properties62;    //Pink21
    private String properties63;    //AmorUs
    private String properties64;    //DanzonCosmetics

    public ItemSAP(Long idItem, String itemCode, String itemName, String vatLiable, String taxType, String ncmCode, String indirectTax, char itemType, String mainsupplier, int itemsGroupCode, Double salesUnitWeight, int salesWeightUnit, double inventoryWeight, double saleItemsPerUnit, String planningSystem, String procurementMethod, String barCode, String uCLASIFSTORE, String uCodigo, String salesUnit, double sqlesQtyPerPackUnit, String inventoryUOM, String purchaeUnit, String purchasePackagingUnit, String salesPackagingUnit, String sww, String properties1, String properties2, String properties3, String properties4, String properties5, String properties6, String properties7, String properties8, String properties9, String properties10, String properties11, String properties12, String properties13, String properties14, String properties15, String properties16, String properties17, String properties18, String properties19, String properties20, String properties21, String properties22, String properties23, String properties24, String properties25, String properties26, String properties27, String properties28, String properties29, String properties30, String properties31, String properties32, String properties33, String properties35, String properties36, String properties37, String properties38, String properties39, String properties40, String properties41, String properties42, String properties43, String properties44, String properties45, String properties46, String properties47, String properties48, String properties49, String properties50, String properties57, String properties58, String properties59, String properties60, String properties61, String properties62, String properties63, String properties64) {
        this.idItem = idItem;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.vatLiable = vatLiable;
        this.taxType = taxType;
        this.ncmCode = ncmCode;
        this.indirectTax = indirectTax;
        this.itemType = itemType;
        this.mainsupplier = mainsupplier;
        this.itemsGroupCode = itemsGroupCode;
        this.salesUnitWeight = salesUnitWeight;
        this.salesWeightUnit = salesWeightUnit;
        this.inventoryWeight = inventoryWeight;
        this.saleItemsPerUnit = saleItemsPerUnit;
        this.planningSystem = planningSystem;
        this.procurementMethod = procurementMethod;
        this.barCode = barCode;
        this.uCLASIFSTORE = uCLASIFSTORE;
        this.uCodigo = uCodigo;
        this.salesUnit = salesUnit;
        this.sqlesQtyPerPackUnit = sqlesQtyPerPackUnit;
        this.inventoryUOM = inventoryUOM;
        this.purchaeUnit = purchaeUnit;
        this.purchasePackagingUnit = purchasePackagingUnit;
        this.salesPackagingUnit = salesPackagingUnit;
        this.sww = sww;
        this.properties1 = properties1;
        this.properties2 = properties2;
        this.properties3 = properties3;
        this.properties4 = properties4;
        this.properties5 = properties5;
        this.properties6 = properties6;
        this.properties7 = properties7;
        this.properties8 = properties8;
        this.properties9 = properties9;
        this.properties10 = properties10;
        this.properties11 = properties11;
        this.properties12 = properties12;
        this.properties13 = properties13;
        this.properties14 = properties14;
        this.properties15 = properties15;
        this.properties16 = properties16;
        this.properties17 = properties17;
        this.properties18 = properties18;
        this.properties19 = properties19;
        this.properties20 = properties20;
        this.properties21 = properties21;
        this.properties22 = properties22;
        this.properties23 = properties23;
        this.properties24 = properties24;
        this.properties25 = properties25;
        this.properties26 = properties26;
        this.properties27 = properties27;
        this.properties28 = properties28;
        this.properties29 = properties29;
        this.properties30 = properties30;
        this.properties31 = properties31;
        this.properties32 = properties32;
        this.properties33 = properties33;
        this.properties35 = properties35;
        this.properties36 = properties36;
        this.properties37 = properties37;
        this.properties38 = properties38;
        this.properties39 = properties39;
        this.properties40 = properties40;
        this.properties41 = properties41;
        this.properties42 = properties42;
        this.properties43 = properties43;
        this.properties44 = properties44;
        this.properties45 = properties45;
        this.properties46 = properties46;
        this.properties47 = properties47;
        this.properties48 = properties48;
        this.properties49 = properties49;
        this.properties50 = properties50;
        this.properties57 = properties57;
        this.properties58 = properties58;
        this.properties59 = properties59;
        this.properties60 = properties60;
        this.properties61 = properties61;
        this.properties62 = properties62;
        this.properties63 = properties63;
        this.properties64 = properties64;
    }

    public ItemSAP() {
    }
}
