/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad02_ejer01;

import generated.CursoType;
import generated.DatosProf;
import generated.ObjectFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Carlos
 */
public class xml_engine {
  
    //ruta del archivo xml
    private String path;   
    
    //constructor
    public xml_engine(String xml) throws JAXBException, FileNotFoundException {
        this.path = xml;
    }
    
    //metodo para generar el arbol java
    public CursoType getCursoType() throws JAXBException, FileNotFoundException {

        JAXBContext contexto = JAXBContext.newInstance("generated");
        Unmarshaller u = contexto.createUnmarshaller();
        JAXBElement elemento = (JAXBElement) u.unmarshal(new FileInputStream(path));        
        return (CursoType) elemento.getValue();

    }
    
    //metodo para generar el arbol XML
    public String setCursoType(CursoType datos) {

        try {
            JAXBContext contexto1 = JAXBContext.newInstance(CursoType.class);
            Marshaller marshall = contexto1.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            StringWriter salida = new StringWriter();
            ObjectFactory mapeo = new ObjectFactory();
            
            marshall.marshal(mapeo.createCurso(datos),salida);
            
            return salida.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(xml_engine.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
