/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsfinalproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author AQIB HASSAN
 */

class BubbleSort
{
    MainFrame MF;
    int ck=0;
    int swap=0;
    BubbleSort( MainFrame MF)
    {
        this.MF=MF;
    }
     private int array[];
    void bubbleSort(int arr[]) 
	{ 
            int n=arr.length;
		int i, j, temp; 
		boolean swapped; 
		for (i = 0; i < n - 1; i++) 
		{ 
			swapped = false; 
			for (j = 0; j < n - i - 1; j++) 
			{ ck++;
				if (arr[j] > arr[j + 1]) 
				{ swap+=3;
					// swap arr[j] and arr[j+1] 
					temp = arr[j]; 
					arr[j] = arr[j + 1]; 
					arr[j + 1] = temp; 
					swapped = true; 
				} 
			} 

			// IF no two elements were 
			// swapped by inner loop, then break 
			if (swapped == false) 
				break; 
		} 
	} 


	// Function to print an array 
	 void printArray(int arr[]) 
	{ 
            int size=arr.length;
		int i; 
                MF.emptyT1();
		for (i = 0; i < size; i++) 
                {
                      System.out.print(arr[i] + " "); 
             MF.appendT1(""+arr[i]+"\n");
                }
			
		System.out.println(); 
                  System.out.println("swaps: "+swap);
                 System.out.println("chks: "+ck);
	} 


     int GetCHKComplexity()
     {
         return this.ck;
         
     }
     int GetSwapComplexity()
     {
             System.out.println("swaps: "+this.swap);
         return this.swap;
     
     }
             
 
   
}
 class MyQuickSort {
     
      int CHKComplexity=0;
    int SwapComplexity=0;
    private int array[];
    private int length;
    MainFrame MF;
    MyQuickSort(MainFrame MF)
    {
        this.MF=MF;
    }
     int ck=0;
    int swap=0;
	int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than or 
			// equal to pivot 
                    ck++;
			if (arr[j] <= pivot) 
			{ 
				i++; 
                                    this.swap+=3;
				// swap arr[i] and arr[j] 
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

                    this.swap+=3;
		// swap arr[i+1] and arr[high] (or pivot) 
		int temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	void sort(int arr[], int low, int high) 
	{ 
		if (low < high) 
		{ 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			sort(arr, low, pi-1); 
			sort(arr, pi+1, high); 
		} 
	} 

	/* A utility function to print array of size n */
    void printArray(int arr[]) 
	{ 
		int n = arr.length; 
                MF.emptyT3();
		for (int i=0; i<n; ++i) 
                {	
//                    System.out.print(arr[i]+" "); 
//		System.out.println(); 
                      MF.appendT3(""+arr[i]+"\n");}
//                 System.out.println("swaps: "+this.swap);
//                 System.out.println("chks: "+this.ck);
	} 

        int GetCHKComplexity()
     {
         return this.ck;
     }
     int GetSwapComplexity()
     {
         return swap;
     }
   
}

public class DSFinalProject {
    
    /**
     * @param args the command line arguments
     */
    static int []Array1;
   static int []Array2;
   static ArrayList<Integer> data;
    public static void main(String[] args) throws IOException {
         
 data= new ArrayList<Integer>(); 
         
          MainFrame MF= new MainFrame();
 new StartingFrame(MF).setVisible(true);
//          MF.setVisible(true);
//         MF. jLabel8.setVisible(true);
            FileReader fr=null; 
        try
        { 
            fr = new FileReader("output.txt"); 
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("File not found"); 
        } 
        int f=0;
  
        // read from FileReader till the end of file 
  BufferedReader br=new BufferedReader(fr);    
            MF.emptyT2();
          int i;  
          String num="";
                  int number=0;
          while((i=br.read())!=-1){  
              
              if((char)i==',')
              {
                  number =Integer.parseInt( num);
                  MF.appendT2(""+number+"\n");
                  data.add(number);
           
                    num="";
              }
              else
              {
                 num+=(char)i;
              }
             
          }  
         

       
     Array1=new int[data.size()];
        System.out.println("data size:"+data.size());
       for(int m=0;m<data.size();m++)
       {
           Array1[m]=data.get(m);
       }
      Array2=Array1;
         
    }
    
}
