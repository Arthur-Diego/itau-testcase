package com.itau.testcase.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidTesteMain {

    public static void main(String args[]){

        String passPaternNumero = ".*\\d.*"; //Verifica se tem um numero
        String passPaternMinuscula = ".*[a-z].*"; //LETRA MINUSCULA
        String passPaternMaiscula = ".*[A-Z].*"; //LETRA MINUSCULA
        String passPaternSpecial = ".*[!@#$%^&*()+-].*";//Caracter Especial
        String duplicate = ".*([A-Za-z0-9])(?=.+\\1).*";//Caracter Especial
        String duplicateSequence = ".*([A-Za-z0-9!@#$%^&*()+-])\\1.*";

        String passPaternLetra = "."; //Verifica se tem um numero
        String passPaternDuplicate = "\\b(\\w+)\\s+\\1\\b"; //Verifica se tem um numero



        String passPatern2 = "[0-9]?";
        Pattern p = Pattern.compile(passPaternDuplicate);


        String pass = "AbTp9!f7K";
       // Matcher m = p.matcher(pass);
        //System.out.println(m.matches());


        System.out.println(pass.matches(passPaternSpecial));

//        String password = "AbTp9!foaa";
//        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+-])(?=\\S+$)(?!.*(.)\\1).{8,}";
//        CharSequence c = password;
//        Boolean s =  c.length() != c.chars().distinct().count();
//        System.out.println(password.matches(pattern) && !s);


    }

    private static Boolean validPass(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+-])(?=\\S+$)(?!.*(.)\\1).{8,}";

        //Pattern p = Pattern.compile("([a-z0-9])\\1{2,}");
       // Matcher m = p.matcher(password);
        String regex = "([a-z])\\1{2}";

        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("a9bTp!foV");

        System.out.println("Any2:"+ m.find());
        String patternRepeatLettersAnyWhere = "^(?:([A-Za-z])(?!.\\1))";
        String patternRepeatLettersAnyWhere2 = "(?!.*([A-Za-z0-9])\\1{2})";
        String patterRepeatSequenceLetters = "(?!.*(.)\\1)";
      //  System.out.println(password.matches(pattern));
      //  System.out.println(password.matches(patternRepeatLettersAnyWhere));
      //  System.out.println(password.matches(patternRepeatLettersAnyWhere2));


      //  System.out.println(password.matches(patterRepeatSequenceLetters));
        return password.matches(pattern) && password.matches(patternRepeatLettersAnyWhere) && password.matches(patterRepeatSequenceLetters);
    }
}
