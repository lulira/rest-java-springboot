package br.com.lulira.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    // metodo que mapeia entidade para DTO e vice-versa
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();


    /*esse metodo faz conversão de entidade para DTO e de DTO para entidade
    * recebe "O" origin do tipo Person e converte para PersonDTO.Class
    * onde origin é o objeto original e o destination é o PersonDTO
    * foi usado generics na implementação desses metodos*/

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }


    /*esse segundo metodo está convertendo uma lista de objetos, ele cria uma lista temporaria
    * de objetos faz a iteração de cada objeto convertido sobre os objetos originais
    * ao final trazendo a lista de objetos convertidos*/
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {

        List<D> destinationObjects = new ArrayList<D>();
        for (Object o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
