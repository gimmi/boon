package org.boon.json;

import org.junit.After;
import org.junit.Test;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.boon.Boon.puts;
import static org.boon.Exceptions.die;
import static org.boon.Lists.list;
import static org.boon.Str.lines;

public class JsonParserUsingCharacterSourceTest extends JsonParserBaseTest {

    JsonParser jsonCharSrcParser;

    public JsonParser parser () {
        jsonCharSrcParser =  new org.boon.json.implementation.JsonParserUsingCharacterSource (  );
        return  jsonCharSrcParser;
    }


    @After
    public void after () {
        //TODO either have it auto-close or delegate close to char source and then to reader
        jsonCharSrcParser.close ();
    }



    @Test
    public void simpleString () {

        String str  = (String) jsonParser.parse (
                new String ("\"file\"")
        );

        boolean ok = str.equals ( "file" ) || die ( str );


    }


    @Test
    public void simpleNumber () {

        int i  = (int) jsonParser.parse (
                new StringReader ("123")
        );

        boolean ok = i == 123 || die ( "" + i );


    }

    @Test
    public void simpleFloat2 () {

        double f  = (double) jsonParser.parse (
                new StringReader ("1.1")
        );

        boolean ok = f == 1.1d || die ( "" + f );


    }


    @Test
    public void simpleListFromReader () {

        List v  = (List ) jsonParser.parse (
                new StringReader ("[1,2,3]")
        );

        boolean ok = list(1,2,3).equals ( v ) || die ( "" + v );


    }

    @Test
    public void simpleStringList () {

        List v  = (List ) jsonParser.parse (
                new String ("[\"abc\",\"\",3]")
        );

        boolean ok = list("abc","",3).equals ( v ) || die ( "" + v );


    }

    @Test
    public void simpleFloat () {


        Map<String, Object> map = ( Map<String, Object> ) jsonParser.parse ( Map.class,
                new String (lines (

                        "{ \"v\":1.1}"
                ))
        );


        puts ( "map", map );
        Object o = map.get ( "v" );

        if (o instanceof BigDecimal ) {
            o = ((BigDecimal) o).doubleValue();
        }

        boolean ok = o.equals ( 1.1 ) || die ( "map " + map.get ( "v" ) );
    }


}