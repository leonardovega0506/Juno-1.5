package mx.com.ananda.cronos.juno.model.dto.http;

import lombok.Data;

import javax.persistence.*;

@Data
public class ItemDTO {
        private Long idItem;
        private String itemcode;
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


}
