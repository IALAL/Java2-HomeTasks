package Hometask_5;

 class ArrOper {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
     float[] arr1 = new float[h];
     float[] arr2 = new float[h];

     public static void main(String[] args) {

         ArrOper Arrop = new ArrOper();
         Arrop.FillArr();
         System.out.println("Time with x1 thread: " + Arrop.MathArr());
         Arrop.FillArr();
         System.out.println("Time with x2 threads: " + Arrop.ThreadedMathArr());
         Arrop.FillArr();
         System.out.println("Time with x2 threads (Test): " + Arrop.TestThreadedMathArr());

     }

    void FillArr(){
        for (int i = 0; i < size; i++) {
            arr[i]=1;
        }
    }


     long MathArr(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis()-startTime;
    }

     long ThreadedMathArr(){
       long startTime = System.currentTimeMillis();
       System.arraycopy(arr, 0, arr1, 0, h);
       System.arraycopy(arr, h, arr2, 0, h);

       Thread Th1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i = 0; i < h; i++) {
                     arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                 }
//                 System.arraycopy(arr1, 0, arr, 0, h);
                 System.out.println("Th1 end");
             }
         });

         Thread Th2 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i = 0; i < h; i++) {
                     arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                 }
//                 System.arraycopy(arr2, 0, arr, h, h);
                 System.out.println("Th2 end");
             }
         });

         Th1.start();
         Th2.start();

         try {
             Th1.join();
         }catch (InterruptedException e){
             throw new RuntimeException(e);
         }
         try {
             Th2.join();
         }catch (InterruptedException e){
             throw new RuntimeException(e);
         }

         System.arraycopy(arr1, 0, arr, 0, h);
         System.arraycopy(arr2, 0, arr, h, h);

         return System.currentTimeMillis()-startTime;
     }
     // случай при котором массив не дробится, а поток начинает работать с его середины
     long TestThreadedMathArr(){
         long startTime = System.currentTimeMillis();

         Thread Th1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i = 0; i < h; i++) {
                     arr[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                 }
                 System.out.println("Th1 end");
             }
         });
         
         Thread Th2 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i = h; i < size; i++) {
                     arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                 }
                 System.out.println("Th2 end");
             }
         });

         Th1.start();
         Th2.start();

         try {
             Th1.join();
         }catch (InterruptedException e){
             throw new RuntimeException(e);
         }
         try {
             Th2.join();
         }catch (InterruptedException e){
             throw new RuntimeException(e);
         }

         return System.currentTimeMillis()-startTime;
     }
 }
