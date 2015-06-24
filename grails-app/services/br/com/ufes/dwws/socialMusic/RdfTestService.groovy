package br.com.ufes.dwws.socialMusic

import grails.transaction.Transactional
import org.openrdf.model.Literal
import org.openrdf.model.Model
import org.openrdf.model.Resource
import org.openrdf.model.Statement
import org.openrdf.model.ValueFactory
import org.openrdf.model.impl.NamespaceImpl
import org.openrdf.model.impl.ValueFactoryImpl
import org.springframework.web.method.annotation.ModelFactory

@Transactional
class RdfTestService {

    def test1() {
        ValueFactory factory = ValueFactoryImpl.getInstance();

        URI bob = factory.createURI("http://ta.sandrart.net/-person-2162.rdf");
        URI name = factory.createURI("http://ta.sandrart.net/-artwork-2854");
        Literal bobsName = factory.createLiteral("Bob");
        Statement nameStatement = factory.createStatement(bob, name, bobsName);

    }


    def test2() {
        ValueFactory factory = ValueFactoryImpl.getInstance();

        URI bob = factory.createURI("http://ta.sandrart.net/-person-2162.rdf");
        URI name = factory.createURI("http://ta.sandrart.net/-artwork-2854");
        Literal bobsName = factory.createLiteral("Bob");
        Statement nameStatement = factory.createStatement(bob, name, bobsName);
    }


    def test3() {
        Model model = ModelFactory.createDefaultModel();
        String myNS = "http://localhost:8080/CDITravel/data/TourPackage/";
        String grNS = "http://purl.org/goodrelations/v1#";
        //model.setNsPrefix("gr", grNS);
        model.setNamespace(new NamespaceImpl('gr', grNS))
        //Resou
        Resource grOffering = ResourceFactory.createResource(grNS + "Offering");
        Resource grPriceSpecification = ResourceFactory.createResource(grNS + "PriceSpecification");
//        Property gravailabilityStarts = ResourceFactory.createProperty(grNS + "availabilityStarts");
//        Property gravailabilityEnds = ResourceFactory.createProperty(grNS + "availabilityEnds");
//        Property grhasPriceSpecification = ResourceFactory.createProperty(grNS + "hasPriceSpecification");
//        Property grhasCurrencyValue = ResourceFactory.createProperty(grNS + "hasCurrencyValue");
    }



}
