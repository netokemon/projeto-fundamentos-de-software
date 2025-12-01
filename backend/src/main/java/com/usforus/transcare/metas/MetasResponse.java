package com.usforus.transcare.metas;


public record MetasResponse(
        Long id,
        String nome
) {


    public static MetasResponse fromEntity(Metas metas) {
        return new MetasResponse(

                metas.getId(),
                metas.getNome()
        );
    }

}



