package com.wesdom.compa.backend.database.enums;

public enum FloraType {

    //Flora type
    TREE("ARBOL"),PLANT("PLANTA"),CROP("CULTIVO"),
    //Flora abundance
    HIGH("ALTA"),MEDIUM("MEDIA"),LOW("BAJA");

    private String name;

    private FloraType(String name){
        this.name = name;
    }
}
