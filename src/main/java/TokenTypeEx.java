/**
 * Created by user on 22.03.15.
 */
public enum TokenTypeEx {

    TEXT("[^(#{1-3}|\\*{1-3}|\\n{1}|\\[{1}|\\]{1})]*"), //done
    H1("(([^#]*[#]{1}[^#]*){2})*"),//both side -четное число входждений
    H2("(([^#]*[#]{2}[^#]*){2})*"),//both side
    H3("(([^#]*[#]{3}[^#]*){2})*"),//both side
    P_("\\n{1}"), //both side - should be checked pragmatically
    LINK_OPEN("\\[{1}"),
    LINK_CLOSE("\\]{1}"),
    EM("(([^\\*]*[\\*]{1}[^\\*]*){2})*"), //both side
    STRONG("(([^\\*]*[\\*]{2}[^\\*]*){2})*");//both side

    /*    P_OPEN(),
    P_CLOSE(),*/
/*    EM_OPEN(),
    EM_CLOSE(),
    STRONG_OPEN(),
    STRONG_CLOSE(),*/


  /*  MINUS("\\-"),;*/


    /* /:escape
     \#:<h1/>
             \##:<h2/>
             \###:<h3/>
             \n:<p></p>
             *:<em></em>
             **:<strong></strong>
             [:link
     ]:link
 */
    private String regex;

    TokenTypeEx(String regex) {
        this.regex = regex;
    }
}


