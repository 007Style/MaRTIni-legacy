/* This file contains variables from the file global.h in MADRASV3 */


public abstract class SimSetup {
  static final int iSeed = 0;

  public static class Map {
    
      static final double dScale = 0.07;
      static final double dObjScale = 0.16;
      static final boolean BaseColors = true;
      static final boolean BaseLines= true;
    
  }

  public static class Time{
    static double dSimTime;         /*Simulation run time*/
    static double dTransTime;       /*transient time */
    static double dPosTmRes;        /*time resolution for position updates*/
    static double dSirTmStep;       /*time between sir measurements*/
    static double dRsrcTmStep;      /*time between two resource management event*/
    static double dPCTmStep;    /*time between two power control iterations*/
    static int TimeScale = 1000; /*multiply all times by this factor */
                                /*conversion from secs to millsecs perhaps */
  }

  public static class Speed {
    static final double dMinSpeed = 25;        /* 60 km/hr */
    static final double dMaxSpeed = 90;        /* 120 km/hr */
    static final double dMeanSpeed = 25;       /* 90 km/hr */
    static final double dSigmaSpeed = 4.17;
    static final double dProbAhead = 0.6;       /* Probability of going straight ahead */
  }

  public static class Statistics {
    static int iTermIndex;          /*Terminal counter*/
    static int iNoBlocked;          /*Blocked terminal counter*/
    static int iNoArvl;
    static int iNoBlkd0;
    static int iNoBlkd1;
    static int iNoBlkd2;
    static int iNoDropped;          /*Forced termination counter*/
    static int iNoFinished;         /*Finished call counter*/
    static int iNoInterAtmpt;       /*Number of handoff attempts*/
    static int iNoSucInterHo;       /*Number of successful handoffs*/
    static int iNoFailInterHo;      /*Number of failed handoffs*/
    static int iNoIntraAtmpt;
    static int iNoSucIntraHo;
    static int iNoFailIntraHo;
    static int iNoFailHo0;
    static int iNoFailHo1;
    static int iNoFailHo2;
  }

  public static class Power {
   static double dNoisePwr;        /* Power of background thermal noise */
   static double dMaxPwr;          /* Maximum transmit power */
 
  }

  public static class Propogation {
    static double dPropCoeff;       /* propagation coefficient */
    static double MANHATTAN;
    static double Px;
    static double N1;
    static double N2;
    static double Ny;
    static double Xb;
    static double Y0;
    static double Y1;
    static double Y2;
    static double Py;
    static double Yb;
    
    static double dLgNrmlSigma;     /* variance of shadow fading */
    static double dCorrDist;        /* shadow fading correlation distance*/
    
  }
  public static class Resource {
    
    static double dErlang = 0.40;          /*traffic load, erl/cell/ch */
    static double dArrRate = (40);         /*Arrival rate of new calls*/
    static double dCallDur=1200000;        /*average call duration */
    static double dSirTarget;       /*The target SIR that DPC tries to achieve */
    static double dSirTargetDB;
    static double dSirDrop;         /*Drop threshold for SIR */
    static double dSirDropDB;
    static double dSirNew;          /*th to admit new call */
    static double dSirNewDB;
    static double dSirHo;           /*threshold to handoff out */
    static double dSirHoDB;
    static double dHysSir;          /*hysterisis for handoff */
    static double dHysSirDB;
    static double dHysStr;          /*signal strength hysterisis for handoff */
    static double dHysStrDB;
    static double dOutLimit;        /*Time for which SIR can fall below dSirDrop*/
    static double dHoDelay;         /*Time delay for handoff */
    static double dSirThre2;   
    
  }

  public static class Grid {
    static int iDimension = 2;          /* dimension of simulation */
    static int iNoBlock = 12;            /*0:19, # of blocks in each dimension */
    static int iDistRes = 50;
    static int iBlockSize = 400;
    static double dCellRadius;                           /* cell radius */
    static int dTtlLength = (iNoBlock * iBlockSize) ;   /* (iNoBlock * dBlockSize)*/
    static int iMaxCoord = dTtlLength - iDistRes;       /* Maximum value for either coordinate*/
    static int iPointPBlck = iBlockSize / iDistRes;      /* (dBlockSize / iDistRes) */
    static int iTtlPoints = iNoBlock * iPointPBlck;      /* (iNoBlock * iPointPBlck) */
    static int iNumPoints = 2160;
    static int EAST = 0;
    static int SOUTH = 1;
    static int WEST = 2;
    static int NORTH = 3;
  }

  public static class RadioChannel{
    static String  sBaseFile[];        /* input datafile of base position */
    static double dBaseHt;          /* Height of base antenna */
    static int iNoBase=12;             /*1:20, No. of base stations in the system */
    static int iNoChannel= 30;             /*1:40, Number of channels in the system */
    static int iNoNghbr;            /*0:5, Max number of interference neighbors */
    
  }
  
}

















































