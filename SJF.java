import java.util.Scanner;

public class SJF {

        int ID,AT,BT,CT,TAT,WT;
        static float avgwt,avgta;
        
        static int size=3;

        static SJF p[]=new SJF[size];
        
        public SJF(int id,int arr, int time){
            this.ID=id;
            this.AT=arr;
            this.BT=time;
        }
        
        public static void main(String []args){
    
            Scanner sc = new Scanner(System.in);

            for(int i=0;i<size;i++){
                int arr,time;

                System.out.println("\nEnter Process"+(i+1));

                System.out.print("Arrival Time: ");
                arr=sc.nextInt();
                System.out.print("Burst Time: ");
                time=sc.nextInt();

                p[i]=new SJF((i+1),arr, time);
            }
            
            sort_table();
            calculation_table();
            disp_table();
        
        }

        public static void sort_table(){
            SJF temp =new SJF(0,0, 0);
            for(int i = 0; i<size; i++)
            {
                for(int  j=0;  j<size-(i+1) ; j++)
                {
                    if( p[j].BT > p[j+1].BT )
                    {
                        temp=p[j];
                        p[j]=p[j+1];
                        p[j+1]=temp;
                    }
                }
            }
        }


        public static void calculation_table(){

            for(int  i = 0 ; i < size; i++)
            {
                if( i == 0){
                    p[i].CT = p[i].AT + p[i].BT;
                }else{
                 if( p[i].AT > p[i-1].CT){
                        p[i].CT=p[i].AT+p[i].BT;
                    }else
                        p[i].CT = p[i-1].CT + p[i].BT;
                    }

                    p[i].TAT = p[i].CT - p[i].AT ;           // turnaround time= completion time- arrival time
                    p[i].WT = p[i].TAT - p[i].BT ;          // waiting time= turnaround time- burst time
                    avgwt += p[i].WT ;                     // total waiting time
                    avgta += p[i].TAT ;                   // total turnaround time
            }

                    
        }     
    


        public static void disp_table(){

            System.out.println(" |Proc| "+" |Arri| "+" |Burs| "+" |Compl| "+" |Turn| "+" |Wait| ");
            for(int i=0;i<size;i++){
                System.out.println("   P"+p[i].ID+"\t    "+p[i].AT+"\t   "+ p[i].BT+"\t    "+p[i].CT+"\t    "+ p[i].TAT+"\t    "+p[i].WT);
                System.out.println();
            }
            System.out.println("\naverage waiting time: "+(float) (avgwt/size));     // printing average waiting time.
            System.out.println("average turnaround time:"+(float) (avgta/size));    // printing average turnaround time.
            System.out.println("Throughput: "+(float)(size/avgta));
        }
}




 