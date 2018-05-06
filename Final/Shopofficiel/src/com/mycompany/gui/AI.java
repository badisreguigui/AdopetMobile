package com.mycompany.gui;

import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mac
 */

public class AI {
    
    public int a = 0;
    public String placeholder="";
    
    private Resources theme;
    
    private static AI instance = new AI();
    
    List listA = new ArrayList();
    List listB = new ArrayList();
    
    String rep = "";
        
    
    private static final String[] BAD_WORDS = {"fuck", "shit"};
    
    private static final String[] HELLO_WORDS = {"hey","hi","hej","hello","hola","hi bot","hello bot","hej bot","howdy"};
    
    private static final String[] DOGS_WORDS = {"dog", "cat"};
    
    private static final String[] FINE_WORDS = {"how are you","how r u","how r you","how are u","how are ya"};

    
    private static final String CANT_ABIDE_SUCH_LANGUAGE = "I can't abide such language... Clean up your act...";
    
    private static final String WHY_ARE_YOU_CONCERNED = "Why are you concerned about ";

    private static final String TOO_LITTLE_DATA_PLEASE_TELL_ME_MORE = "Too little data. Please tell me more...";
    
    public static String getResponse(String question) throws IOException {
        return instance.getResponseToQuestion(question.toLowerCase());
    }

    private String getResponseToQuestion(String question) throws IOException {
        
        String cat = "Persian";
        String cat1 = "Birman";
        String dog = "Labrador";
        String dog1 = "Golden";
        String adopet = "Adopet";
        String gay = "gay";
        String name = "name";
        String myname = "ross";
        
        
        
//        if (a<3) {

        if(question.startsWith("haja")) {
            System.out.println(rep);
            
            if (rep == "") {
                listA.add(question);
                System.out.println(listA);
                System.out.println(listB);
                
                return "what am i suppose to answer please type answer: ";   
            } else {
                return rep;
            }
             
        }
        
        if(question.startsWith("answer: ")) {
            question = question.substring(8);
            listB.add(question);
            System.out.println(listA);
            System.out.println(listB);
            rep = question;
            return "your answer has been submitted";  
        }
               
        if(has(question, BAD_WORDS)) {          
            return CANT_ABIDE_SUCH_LANGUAGE;
        }
        
        if(question.startsWith("please")) {
            return "You don't have to be so polite";
        }
        
        if(question.startsWith("tinder")) {
            return "Please type /tinder to access tinder page";
        }
        
        if(question.startsWith("/tinder")) {
//            return "Please type /tinder to access tinder page";
            new tinder(theme).show();
        }
        
        if(question.startsWith("say ")) {
            return question.substring(4);
        }
        
        if(has(question, DOGS_WORDS)) {
            return "If you want to know more about animals just type any dog/cat breed";
        }
        
        if(has(question, HELLO_WORDS)) {
            return "Hello again human";
        }
        
        if(has(question, FINE_WORDS)) {
            return "Fine, thank you :D ";
        }
        
        if( question.toLowerCase().indexOf(name.toLowerCase()) != -1 ) {
            System.out.println("sss");
            return "My name is ROSS.";
        }
        
        if( question.toLowerCase().indexOf(myname.toLowerCase()) != -1 ) {
            return "WOW ! How did you know my name ?!! Are you a robot too?";
        }
        
        if((question.startsWith("tell")) && ( question.toLowerCase().indexOf(cat.toLowerCase()) != -1 ) ){
            return "The Persian cat is a long-haired breed of cat characterized by its round face and short muzzle. It is also known as the Persian Longhair in the English-speaking countries.";
        }
        
        if((question.startsWith("tell")) && ( question.toLowerCase().indexOf(cat1.toLowerCase()) != -1 ) ){
            return "The Birman, also called the \"Sacred Cat of Burma\", is a domestic cat breed. The Birman is a long-haired, color-pointed cat distinguished by a silky coat, deep blue eyes, and contrasting white \"gloves\" or \"socks\" on each paw.";
        }
        
        if((question.startsWith("tell")) && ( question.toLowerCase().indexOf(dog.toLowerCase()) != -1 ) ){
            return "The Labrador Retriever, or just Labrador, is a type of retriever-gun dog. The Labrador is one of the most popular breeds of dog in Canada, the United Kingdom and the United States.";
        }
        
        if((question.startsWith("tell")) && ( question.toLowerCase().indexOf(dog1.toLowerCase()) != -1 ) ){
            return "The Golden Retriever is a large-sized breed of dog bred as gun dogs to retrieve shot waterfowl such as ducks and upland game birds during hunting and shooting parties.";
        }
        
        if ( question.toLowerCase().indexOf(adopet.toLowerCase()) != -1 ) {
            return "Adopet is an application developed by a team based in tunisia, you can check our website www.adopet.tn";
        }
        
        if ( question.toLowerCase().indexOf(gay.toLowerCase()) != -1 ) {
            return "I'm not gay, i'm a robot";
        }
        
        if(question.length() < 6) {
            return TOO_LITTLE_DATA_PLEASE_TELL_ME_MORE;
        }
        
//        }
//        else if (a == 3){
//            System.out.println(" het answer");
//            a++;
//            return "what am i supposed to answer?";
//        }
        
        
        
//        else if(a == 4){
//            placeholder = question;
//            a++;
//            System.out.println("final");
//            return placeholder ;
//        }else {
//            if(question.startsWith("njareb")) {
//            return placeholder;  
//            }
//        }
        
        
        List<String> tokens = StringUtil.tokenize(question, " .,;\"':-?!-_");
        return WHY_ARE_YOU_CONCERNED + tokens.get(tokens.size() - 1);
        
    }
    
    private boolean has(String question, String[] words) {
        for(String s : words) {
            if(question.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }
}
