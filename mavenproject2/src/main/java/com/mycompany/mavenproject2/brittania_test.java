/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.cost.VehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.ManhattanCosts;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.core.util.VehicleRoutingTransportCostsMatrix;
import java.util.Collection;

/**
 *
 * @author utkarsh
 */
public class brittania_test {
    public static void main(String[] args) {

		/*
         * get a vehicle type-builder and build a type with the typeId "vehicleType" and one capacity dimension, i.e. weight, and capacity dimension value of 2
		 */
        VehicleTypeImpl vehicleType1 = VehicleTypeImpl.Builder.newInstance("vehicleType1")
          .addCapacityDimension(0,1196).addCapacityDimension(1,9000).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType2 = VehicleTypeImpl.Builder.newInstance("vehicleType2")
          .addCapacityDimension(0,964).addCapacityDimension(1,6000).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType3 = VehicleTypeImpl.Builder.newInstance("vehicleType3")
          .addCapacityDimension(0,920).addCapacityDimension(1,7000).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType4 = VehicleTypeImpl.Builder.newInstance("vehicleType4")
          .addCapacityDimension(0,850).addCapacityDimension(1,6000).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType5 = VehicleTypeImpl.Builder.newInstance("vehicleType5")
          .addCapacityDimension(0,613).addCapacityDimension(1,4500).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType6 = VehicleTypeImpl.Builder.newInstance("vehicleType6")
          .addCapacityDimension(0,330).addCapacityDimension(1,3000).setCostPerDistance(1)
          .build();
        VehicleTypeImpl vehicleType7 = VehicleTypeImpl.Builder.newInstance("vehicleType7")
          .addCapacityDimension(0,110).addCapacityDimension(1,1000).setCostPerDistance(1)
          .build();


		/*
         * get a vehicle-builder and build a vehicle located at (10,10) with type "vehicleType"
		 */
        VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("vehicle1");
        vehicleBuilder1.setStartLocation(Location.newInstance("0"));
        vehicleBuilder1.setType(vehicleType1);
        VehicleImpl vehicle1 = vehicleBuilder1.build();
        
        VehicleImpl.Builder vehicleBuilder2 = VehicleImpl.Builder.newInstance("vehicle2");
        vehicleBuilder2.setStartLocation(Location.newInstance("0"));
        vehicleBuilder2.setType(vehicleType2);
        VehicleImpl vehicle2 = vehicleBuilder2.build();
        
        VehicleImpl.Builder vehicleBuilder3 = VehicleImpl.Builder.newInstance("vehicle3");
        vehicleBuilder3.setStartLocation(Location.newInstance("0"));
        vehicleBuilder3.setType(vehicleType3);
        VehicleImpl vehicle3 = vehicleBuilder3.build();
        
        VehicleImpl.Builder vehicleBuilder4 = VehicleImpl.Builder.newInstance("vehicle4");
        vehicleBuilder4.setStartLocation(Location.newInstance("0"));
        vehicleBuilder4.setType(vehicleType4);
        VehicleImpl vehicle4 = vehicleBuilder4.build();
        
        VehicleImpl.Builder vehicleBuilder5 = VehicleImpl.Builder.newInstance("vehicle5");
        vehicleBuilder5.setStartLocation(Location.newInstance("0"));
        vehicleBuilder5.setType(vehicleType5);
        VehicleImpl vehicle5 = vehicleBuilder5.build();
        
        VehicleImpl.Builder vehicleBuilder6 = VehicleImpl.Builder.newInstance("vehicle6");
        vehicleBuilder6.setStartLocation(Location.newInstance("0"));
        vehicleBuilder6.setType(vehicleType6);
        VehicleImpl vehicle6 = vehicleBuilder6.build();
        
        VehicleImpl.Builder vehicleBuilder7 = VehicleImpl.Builder.newInstance("vehicle7");
        vehicleBuilder7.setStartLocation(Location.newInstance("0"));
        vehicleBuilder7.setType(vehicleType7);
        VehicleImpl vehicle7 = vehicleBuilder7.build();

		/*
         * build services at the required locations, each with a capacity-demand of 1.
		 */
       Service service1 =  Service.Builder.newInstance("1")
          .setName("23029")
          .setLocation(Location.newInstance("1")).addTimeWindow(0, 0)
          .addSizeDimension(0,1).addSizeDimension(1,15).build();
       
       Service service2 =  Service.Builder.newInstance("2")
          .setName("15457")
          .setLocation(Location.newInstance("2")).addTimeWindow(0, 0)
          .addSizeDimension(0,287).addSizeDimension(1,1788).build();
       
       /*Service service3 =  Service.Builder.newInstance("3")
          .setName("23688")
          .setLocation(Location.newInstance(5,7)).addTimeWindow(0, 0)
          .addSizeDimension(0,405).addSizeDimension(1,2287).build();
       
       Service service4 =  Service.Builder.newInstance("4")
          .setName("25110")
          .setLocation(Location.newInstance(5,7)).addTimeWindow(0, 0)
          .addSizeDimension(0,0).addSizeDimension(1,5).build();
       
       Service service5 =  Service.Builder.newInstance("5")
          .setName("22196")
          .setLocation(Location.newInstance(5,7)).addTimeWindow(0, 0)
          .addSizeDimension(0,134).addSizeDimension(1,859).build();
       
       Service service6 =  Service.Builder.newInstance("6")
          .setName("23717")
          .setLocation(Location.newInstance(5,7)).addTimeWindow(0, 0)
          .addSizeDimension(0,127).addSizeDimension(1,843).build();
       */
       Service service3 =  Service.Builder.newInstance("3")
          .setName("23029a")
          .setLocation(Location.newInstance("3")).addTimeWindow(0, 0)
          .addSizeDimension(0,1).addSizeDimension(1,2514).build();
       /*
       Service service8 =  Service.Builder.newInstance("8")
          .setName("25110a")
          .setLocation(Location.newInstance(5,7)).addTimeWindow(0, 0)
          .addSizeDimension(0,0).addSizeDimension(1,1081).build();
*/
    VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);
    
        costMatrixBuilder.addTransportTime("0", "1", 68);
        costMatrixBuilder.addTransportTime("0", "2", 67);
        costMatrixBuilder.addTransportTime("0", "3", 68);
        costMatrixBuilder.addTransportTime("2", "3", 999999999);
        costMatrixBuilder.addTransportTime("1", "3", 0);
        costMatrixBuilder.addTransportTime("1", "2", 999999999);
        
        costMatrixBuilder.addTransportDistance("0", "1", 40);
        costMatrixBuilder.addTransportDistance("0", "2", 39);
        costMatrixBuilder.addTransportDistance("0", "3", 40);
        costMatrixBuilder.addTransportDistance("2", "3", 999999999);
        costMatrixBuilder.addTransportDistance("1", "3", 0);
        costMatrixBuilder.addTransportDistance("1", "2", 999999999);
 
       
       
        VehicleRoutingTransportCosts costMatrix = costMatrixBuilder.build();
      
       
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle1).addVehicle(vehicle2).addVehicle(vehicle3).addVehicle(vehicle4).addVehicle(vehicle5).addVehicle(vehicle6).addVehicle(vehicle7) ;
        vrpBuilder.addJob(service1).addJob(service2)
            .addJob(service3)
        ;
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);
        vrpBuilder.setRoutingCost(costMatrix);
        VehicleRoutingProblem problem = vrpBuilder.build();

		/*
         * get the algorithm out-of-the-box.
		 */
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

		/*
         * and search a solution
		 */
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

		/*
         * get the best
		 */
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

//        new VrpXMLWriter(problem, solutions).write("output/problem-with-solution.xml");

        SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);

		/*
         * plot
		 */
       new Plotter(problem,bestSolution).setLabel(Plotter.Label.ID).plot("output/plot.png", "mtw");

        new GraphStreamViewer(problem, bestSolution).labelWith(GraphStreamViewer.Label.ID).setRenderDelay(200).display();
    }
}
