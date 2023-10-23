package mx.com.ananda.cronos.juno.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_vat")
    public String vatLiable;

    @Column(name = "item_taxt")
    public String taxType;

    @Column(name = "item_ncm")
    private String ncmCode;

    @Column(name = "item_taxi")
    public String indirectTax;

    @Column(name = "item_type")
    private char itemType;

    @Column(name = "item_mainsupplier")
    private String mainsupplier;

    @Column(name = "item_group")
    private int itemsGroupCode;

    @Column(name = "item_suw")
    private Double salesUnitWeight;

    @Column(name = "item_swu")
    private int salesWeightUnit;

    @Column(name = "item_iw")
    private double inventoryWeight;

    @Column(name = "item_sipu")
    private double saleItemsPerUnit;

    @Column(name = "item_plannings")
    private String planningSystem;

    @Column(name = "item_procurementm")
    private String procurementMethod;

    @Column(name = "item_barcode")
    private String barCode;

    @Column(name = "item_ucs")
    private String uCLASIFSTORE;

    @Column(name = "item_ucodigo")
    private String uCodigo;

    @Column(name = "item_su")
    private String salesUnit;

    @Column(name = "item_sqpp")
    private double sqlesQtyPerPackUnit;

    @Column(name = "item_iuom")
    private String inventoryUOM;

    @Column(name = "item_pu")
    private String purchaeUnit;

    @Column(name = "item_ppu")
    private String purchasePackagingUnit;

    @Column(name = "item_spu")
    private String salesPackagingUnit;

    @Column(name = "item_sww")
    private String sww;

    /* +++ PROPIEDADES  +++ */
    @Column(name = "item_p1_linea")
    private String properties1;     //Linea

    @Column(name = "item_p2_mixto")
    private String properties2;     //Mixto

    @Column(name = "item_p3_casa")
    private String properties3;     //Casa

    @Column(name = "item_p4_producton")
    private String properties4;     //ProductoN

    @Column(name = "item_p5_lacolors")
    private String properties5;     //LAColors

    @Column(name = "item_p6_rubyrose")
    private String properties6;     //RubyRose

    @Column(name = "item_p7_imnatural")
    private String properties7;     //IMNatural

    @Column(name = "item_p8_peaceglos")
    private String properties8;     //peaceGlos

    @Column(name = "item_p9_curtis")
    private String properties9;     //Curtis

    @Column(name = "item_p10_moda")
    private String properties10;    //Moda

    @Column(name = "item_p11_temporada")
    private String properties11;    //Temporada

    @Column(name = "item_p12_aretesp")
    private String properties12;    //AretesP

    @Column(name = "item_p13_anillop")
    private String properties13;    //AnilloP

    @Column(name = "item_p14_collar")
    private String properties14;    //Collar

    @Column(name = "item_p15_cabello_apple")
    private String properties15;    //CabelloApple

    @Column(name = "item_p16_accesorio_apple")
    private String properties16;    //AccesoriosApple

    @Column(name = "item_p17_pestana_apple")
    private String properties17;    //PestanasApple

    @Column(name = "item_p18_aplicadores_apple")
    private String properties18;    //AplicadoresApple

    @Column(name = "item_p19_depiladores_apple")
    private String properties19;    //DepiladoresApple

    @Column(name = "item_p20_lapiz_and")
    private String properties20;    //LapizAND

    @Column(name = "item_p21_cosmetico_mega")
    private String properties21;    //CosmeticoMega

    @Column(name = "item_p22_donasv")
    private String properties22;    //DonasValerinas

    @Column(name = "item_p23_bpc")
    private String properties23;    //BrosPasCucas

    @Column(name = "item_p24_pd")
    private String properties24;    //PinzasDiademas

    @Column(name = "item_p25_lc")
    private String properties25;    //LigasColetero

    @Column(name = "item_p26_naturone")
    private String properties26;    //Naturone

    @Column(name = "item_p27_prosa41")
    private String properties27;    //Prosa41

    @Column(name = "item_p28_postizas")
    private String properties28;    //Postizas

    @Column(name = "item_p29_hogar")
    private String properties29;    //Hogar

    @Column(name = "item_p30_cocina")
    private String properties30;    //Cocina

    @Column(name = "item_p31_juguete")
    private String properties31;    //Juguetes

    @Column(name = "item_p32_escritura")
    private String properties32;    //Escritura

    @Column(name = "item_p33_cp")
    private String properties33;    //CortaPega

    @Column(name = "item_p34_empaques")
    private String properties34;    //EmpaquesRegalo

    @Column(name = "item_p35_ultramo")
    private String properties35;    //Ultramo

    @Column(name = "item_p36_remate")
    private String properties36;    //Remate

    @Column(name = "item_p37_importacion")
    private String properties37;    //Importacion

    @Column(name = "item_p38_temp_s")
    private String properties38;    //TempSep

    @Column(name = "item_p39_temp_h")
    private String properties39;    //TempHal

    @Column(name = "item_p40_temp_nav")
    private String properties40;    //TempNav

    @Column(name = "item_p41_fiesta")
    private String properties41;    //Fiesta

    @Column(name = "item_p42_cc")
    private String properties42;    //CajaCerrada

    @Column(name = "item_p43_nails")
    private String properties43;    //Nails

    @Column(name = "item_p44_accesorios_megaline")
    private String properties44;    //AccesoriosMegaline

    @Column(name = "item_p45_cabello_megaline")
    private String properties45;    //CabelloMegaline

    @Column(name = "item_p46_accesorios_and")
    private String properties46;    //AccesoriosAND

    @Column(name = "item_p47_cabello_and")
    private String properties47;    //CabelloAND

    @Column(name = "item_p48_pestana_and")
    private String properties48;    //PestanaAND

    @Column(name = "item_p49_depiladores_and")
    private String properties49;    //DepiladoresAND

    @Column(name = "item_p50_aplicadores_And")
    private String properties50;    //AplicadoresAND

    @Column(name = "item_p51_hb")
    private String properties51;    //HebleeCosmetics

    @Column(name = "item_p52_marcas")
    private String properties52;    //Marcas

    @Column(name = "item_p53_restro")
    private String properties53;    //Rostro

    @Column(name = "item_p54_ojos")
    private String properties54;    //Ojos

    @Column(name = "item_p55_labios")
    private String properties55;    //Labios

    @Column(name = "item_p56_cuidado_p")
    private String properties56;    //CuidadoPiel

    @Column(name = "item_p57_accesorios_belleza")
    private String properties57;    //AccesorioBelleza

    @Column(name = "item_p58_bissu")
    private String properties58;    //Bissu

    @Column(name = "item_p59_maravilla")
    private String properties59;    //Maravilla

    @Column(name = "item_p60_byapple")
    private String properties60;    //ByApple

    @Column(name = "item_p61_saniye")
    private String properties61;    //Saniye

    @Column(name = "item_p62_pink21")
    private String properties62;    //Pink21

    @Column(name = "item_p63_amporus")
    private String properties63;    //AmorUs

    @Column(name = "item_p64_danzon_cosmetics")
    private String properties64;    //DanzonCosmetics




}
