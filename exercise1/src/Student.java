package exercise1.src;
import java.util.Random;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;



public class Student {
    
    
    // Attribuutit ------------------------------------------------
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private int id = 0;   
    private double bachelorCredits; 
    private double masterCredits;   
    private String titleOfMastersThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorThesis = ConstantValues.NO_TITLE;
    private int startYear = ConstantValues.CURRENT_YEAR; // Muuttujan oletusarvona nykyinen vuosi
    private int graduationYear = 0;
    private String birthDate = ConstantValues.NO_BIRTHDATE;
    
    // Satunnaislukugeneraattorin initialisointi
    private Random random = new Random();
    
    // Konstruktorit ------------------------------------------
    public Student() {
        id = getRandomId();
    }

    public Student(String lname, String fname) {
        lastName = lname;
        firstName = fname;
        id = getRandomId();
    }   



    // Getterit ja setterit -----------------------------------------
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(final String firstName) {
        if (firstName != null) 
            this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(final String lastName) {
        if (lastName != null) 
            this.lastName = lastName;
    }
    public int getId() {
        return id;
    }    
    // Asettaa id:n, kunhan se on väliltä 1 - 100
    public void setId(int id) {
        if (id > 0 && id <= 100)
            this.id = id;
    }
    public double getBachelorCredits() {
        return bachelorCredits;
    }
    
    // Asettaa kandivaiheen opintopisteet, kunhan ne on sallitulla välillä 0.0 - 300.0
    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= ConstantValues.MIN_CREDIT && bachelorCredits <= ConstantValues.MAX_CREDITS)
            this.bachelorCredits = bachelorCredits;
    }
    public double getMasterCredits() {
        return masterCredits;
    }

    // Asettaa maisterivaiheen opintopisteet, kunhan ne on sallitulla välillä 0.0 - 300.0
    public void setMasterCredits(final double masterCredits) {
        if (masterCredits >= ConstantValues.MIN_CREDIT && masterCredits <= ConstantValues.MAX_CREDITS)
            this.masterCredits = masterCredits;
    }
    
    public String getTitleOfMastersThesis() {
        return titleOfMastersThesis;
    }

    // Asettaa gradun aiheen, kunhan käyttäjäsyöte ei ole null
    public void setTitleOfMastersThesis(final String titleOfMastersThesis) {
        if (titleOfMastersThesis != null) 
            this.titleOfMastersThesis = titleOfMastersThesis;
    }
    
    public String getTitleOfBachelorThesis() {
        return titleOfBachelorThesis;
    }

    // Asettaa kandityön aiheen, kunhan käyttäjäsyöte ei ole null
    public void setTitleOfBachelorThesis(final String titleOfBachelorThesis) {
        if (titleOfBachelorThesis != null) 
            this.titleOfBachelorThesis = titleOfBachelorThesis;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear >= 2000 && startYear <= ConstantValues.CURRENT_YEAR)
            this.startYear = startYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }    

    // Asettaa valmistumisvuoden kun vuosiluku on validi ja oppilaalla on tarpeeksi opintopisteitä
    // Opintopisteet varmistetaan canGraduate metodilla
    public String setGraduationYear(final int graduationYear) {
        if (graduationYear <= 2000 || graduationYear >= ConstantValues.CURRENT_YEAR) 
            return "Check graduation year";  
        if (!canGraduate())
            return "Check the required studies";

        this.graduationYear = graduationYear;        
        return "Ok";
    }

    // Omat lisäämät metodit ---------------------------------------------------
    // Getteri ja setteri syntymäpäivälle    
    public void setBirthdate(String birthdate) {
        birthDate = birthdate;
    }
    public String getBirthdate() {
        return birthDate;
    }

    // Metodit toString funktion printtejä varten 
    // Metodit palauttavat oikean merkkijonon riippuen opintopisteiden määrästä
    private String bacherlorCreditStr(){
        if (bachelorCredits < ConstantValues.BACHELOR_CREDITS) {
            return String.format(" ==> Missing bachelor credits %.1f (%.1f/%.1f)",
            (ConstantValues.BACHELOR_CREDITS - bachelorCredits), bachelorCredits, ConstantValues.BACHELOR_CREDITS);
        } else {
            return String.format(" ==> All required bachelor credits completed (%.1f/%.1f)", bachelorCredits,
            ConstantValues.BACHELOR_CREDITS);
        }
    }
    private String masterCreditStr() {
        
        if (masterCredits < ConstantValues.MASTER_CREDITS) {
            return String.format(" ==> Missing master's credits %.1f (%.1f/%.1f)",
            (ConstantValues.MASTER_CREDITS - masterCredits), masterCredits, ConstantValues.MASTER_CREDITS);
        } else { 
            return String.format(" ==> All required master's credits completed (%.1f/%.1f)", masterCredits,
            ConstantValues.MASTER_CREDITS);
        }
    }


    // Muut metodit ---------------------------------------------------
    
    // Testaa onko oppilas valmistunut
    public boolean hasGraduated() {
        if (graduationYear == 0) 
            return false;
        return true;
    }

    // Testaa voiko oppilas valmistua kunhan tarvittavat opintopisteet löytyy
    // ja kunhan kandin ja gradun aiheet ovat asetettu
    public boolean canGraduate() {

        if (bachelorCredits < ConstantValues.BACHELOR_CREDITS && 
        masterCredits < ConstantValues.MASTER_CREDITS)
            return false;
        
        if (titleOfBachelorThesis == ConstantValues.NO_TITLE && 
        titleOfMastersThesis == ConstantValues.NO_TITLE)
            return false;
        
        return true;
    }

    // Palauttaa opiskelijan opiskeluvuodet. Tsekkaa onko opiskelija valmistunut vai ei.
    public int getStudyYears() {
        if (!hasGraduated())
            return (ConstantValues.CURRENT_YEAR - startYear);
    
        return (graduationYear - startYear);
    }


    // Palauttaa arvotun id:n väliltä 1-100
    private int getRandomId() {
        return random.nextInt(100) + 1;
    }
    
    // Metodi joka printtaa opiskelijan tiedot
    public String toString() {
        
        String statusStr;
        // Opintopistemerkkijonot
        String bachelorCreditsString = bacherlorCreditStr();
        String masterCreditsString = masterCreditStr();

        // Statuksen merkkijono joka määritetään valmistumisen perusteella
        if (hasGraduated())
            statusStr = "The student has graduated in " + getGraduationYear();
        else
            statusStr = "The student has not graduated, yet";

        String birthdate = "\"" + getBirthdate() + "\"";

        return (
            "\nStudent id: " + getId() +
            "\n\tFirstName: " + getFirstName() + ", LastName: " + getLastName() +
            "\n\tDate of birth: " + birthdate + 
            "\n\tStatus: " + statusStr  +
            "\n\tStart year: " + getStartYear() + " (studies have lasted for " + getStudyYears() + " years)" +
            "\n\tBachelorCredits: " + getBachelorCredits() + bachelorCreditsString + 
            "\n\tTitleOfBachelorThesis: \"" + getTitleOfBachelorThesis() + "\"" +
            "\n\tMasterCredits: " + getMasterCredits() + masterCreditsString + 
            "\n\tTitleOfMasterThesis: \"" + getTitleOfMastersThesis() + "\"\n"
        );
    }


    // Tarkistaa oppilaan henkilötunnuksen oikeellisuuden
    // Jos henkilötunnus on virheellinen, palauttaa merkkijonovakion ConstantValues luokasta
    public String setPersonId(final String personID) {
        
        if(!checkPersonIDNumber(personID) || !checkBirthdate(personID))
            return ConstantValues.INVALID_BIRTHDAY;
        if (!checkValidCharacter(personID))
            return ConstantValues.INCORRECT_CHECKMARK;    
        
        return "Ok";
    }
    
    
    // Metodi testaa henkilötunnuksen pituuden sekä välimerkin oikeellisuuden
    private boolean checkPersonIDNumber(final String id) {
        
        char endChar = id.charAt(6);
        
        if (id.length() != 11)
            return false;
        
        if (endChar != '+' && endChar != '-' && endChar != 'A') 
            return false;

        return true;
    }

    // Testaa onko kyseessä karkausvuosi
    private boolean checkLeapYear(final int year) {
        if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) 
            return true;
        return false;
    }


    // Testaa tarkistusmerkin oikeellisuuden
    private boolean checkValidCharacter(final String personID) {
        
        String validcontrolChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXY";
        
        
        // Jos merkkijonosta ei löydy parseInt metodille numeroita, try haara nappaa errorin
        // Muuten ottaa talteen yhdeksännumeroisen luvun muuttamalla sen int muotoon
        int count;

        try {
            count = Integer.parseInt((personID.substring(0, 6) + personID.substring(7, 10)));
        } catch (NumberFormatException parseError) {
            return false;
        }
        

        char controlChar = personID.charAt(10);
        int charValue = 0;

        // Vertaa henkilötunnuksen tarkistusmerkkiä valideihin tarkistusmerkkeihin
        // Antaa merkille arvon sen järjestysluvun mukaan
        for (int i = 0; i < validcontrolChars.length(); i++) {
            if (controlChar == validcontrolChars.charAt(i)) {
                charValue = i;
            }
        }

        // Otetaan jakojäännös kaavan Hetu kaavan mukaan ja pyöristetään luku 
        double validDouble = (double) count % 31;
        long validLong = Math.round(validDouble);

        // Katsotaan kuuluuko arvo oikealle välille
        if (validLong == charValue && validLong <= 31 && validLong >= 0)
            return true;
        
        return false;
    } 
    

    // Tarkistaa syntymäpäivän oikeellisuuden
    private boolean checkBirthdate(final String date) {
        
        // Henkilötunnuksesta otetaan talteen day/month/year ja vuosisata
        String dayStr = date.substring(0, 2);
        String monthStr = date.substring(2, 4);
        String yearStr = date.substring(4, 6);
        char century = date.charAt(6);

        // Muokataan vuosituhat oikeaksi välimerkin perusteella
        if (century == 'A') 
            yearStr = "20" + yearStr; 
        else if (century == '-') 
            yearStr = "19" + yearStr; 
        else if (century == '+') 
            yearStr = "18" + yearStr;         

        // Muutetaan int muotoon
        int dayInt = Integer.parseInt(dayStr);
        int monthInt = Integer.parseInt(monthStr);
        int yearInt = Integer.parseInt(yearStr);
        int days = 0;
        
        // Lista johon talletetaan kuukausien päivien määrät
        List<Integer> daysMonth = new ArrayList<>();
        
        // Kuukausien päivät haetaan listaan käyttämällä ulkoista Month luokkaa
        // If lause tsekkaa vielä karkausvuodet
        for (Month kuukausi : Month.values()) {
            if (!checkLeapYear(yearInt)) {
                days = kuukausi.length(false);
                daysMonth.add(days);
            } else { 
                days = kuukausi.length(true);
                daysMonth.add(days);
            }
        }
        
        // Jos päivät, kuukaudet sekä ovat virheellisiä, metodi palauttaa falsen 
        if (dayInt > daysMonth.get(monthInt - 1) || monthInt > 11 || yearInt >= ConstantValues.CURRENT_YEAR) {
            return false;
        } 
        
        // Metodi asettaa vielä syntymäpäivän joka on haettu Henkilötunnuksesta
        String birthdate =  dayStr.concat(".").concat(monthStr).concat(".").concat(yearStr);
        setBirthdate(birthdate);
        
        return true;
    }
}

