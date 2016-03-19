package org.jvmmx.lambdastream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjercicioStreams {
    
    private static final String PALABRA = "[- .:;,¡!]";
    
    /**
     * Cuenta las líneas del archivo usando el reader provisto
     */
    private void ejercicio1() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("poema.txt"), StandardCharsets.UTF_8)){
            
            System.out.println("lineas: "+reader.lines().count());
            
        }
    }

    /**
     * Crea una lista de palabras sin duplicados del archivo provisto en reader
     * Tip: Ya se cuenta con la expresión PALABRA
     */
    private void ejercicio2() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("poema.txt"), StandardCharsets.UTF_8)){
            
            List<String> lista =
                    reader.lines()
                            .flatMap(s -> Stream.of(s.split(PALABRA)))
                            .filter(s->s.trim().length()>0)
                            .distinct()
                            .collect(Collectors.toList());
            
            System.out.println("lista: "+lista);
            
        }
    }
    
    /**
     * Crea una lista de palabras en minusculas y sin duplicados 
     * del archivo provisto en reader y ordenadas alfabéticamente
     */
    private void ejercicio3() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("poema.txt"), StandardCharsets.UTF_8)){
            
            List<String> lista = 
                    reader.lines()
                        .flatMap(s -> Stream.of(s.split(PALABRA)))
                        .filter(s->s.trim().length()>0)
                        .map(String::toLowerCase)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
            
            System.out.println("lista: "+lista);
        }
    }
    
    /**
     * Modifica el ejercicio3 para que las palabras esten ordenadas por longitud
     */
    private void ejercicio4() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("poema.txt"), StandardCharsets.UTF_8)){
            
            List<String> lista = 
                    reader.lines()
                        .flatMap(s -> Stream.of(s.split(PALABRA)))
                        .filter(s->s.trim().length()>0)
                        .map(String::toLowerCase)
                        .distinct()
                        .sorted((s1, s2) -> s1.length()-s2.length())
                        .collect(Collectors.toList());
            
            System.out.println("lista: "+lista);
            
        }
    }
    
    /**
     * Has un histograma de cada palabra que aparece, todas en minúsculas
     */
    private void ejercicio5() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("poema.txt"), StandardCharsets.UTF_8)){
            
                Map<String, Integer> histograma = 
                    reader.lines()
                        .flatMap(s -> Stream.of(s.split(PALABRA)))
                        .filter(s->s.trim().length()>0)
                        .map(String::toLowerCase)
                        .collect(
                   Collectors.toMap(Function.identity(),s->1,(a,b)->a+b));
         
                System.out.println("histograma: "+ histograma);
        }
    }
    
    /**
     * Complementa el código para la clase RandomWords 
     * con una lista de 1000 palabras agrupalas por longitud
     */
    private void ejercicio6() throws IOException {
        RandomWords rand = new RandomWords();
        List<String> list = rand.createList(1000);
        
        Map<Integer, List<String>> mapa = list.stream()
                .collect(Collectors.groupingBy(s -> s.length()));
        
        System.out.println("palabras: "+ mapa);
    }
    
    
    public static void main(String[] args) throws IOException {
        EjercicioStreams es = new EjercicioStreams();
        System.out.println("Lambdas y Stream API EjercicioStreams");
        System.out.println("Ejercicio 1");
        es.ejercicio1();
        System.out.println("Ejercicio 2");
        es.ejercicio2();
        System.out.println("Ejercicio 3");
        es.ejercicio3();
        System.out.println("Ejercicio 4");
        es.ejercicio4();
        System.out.println("Ejercicio 5");
        es.ejercicio5();
        System.out.println("Ejercicio 6");
        es.ejercicio6();
    }
    
}
