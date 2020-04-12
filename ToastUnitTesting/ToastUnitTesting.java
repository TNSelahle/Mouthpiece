

import android.widget.Toast;

public class ToastUnitTesting {

    ArrayList<Toast> toasty;
    String [] contents;
    Context [] contexts;
    final int toastAmounts=5;
   
    public ToastUnitTesting()
    {
        toasty = new ArrayList();
        contents = new String[toastAmounts];
        contexts = new Context[toastAmounts];
    }
    
    public void ceateToasts()
    {
        for (int i =0 ; i < toastAmounts ; i ++)
        {
            Toast toast = new Toast();
            //create a toast in here with the context and String used in positio i of the contexts and contents arrays
            toasty.add(toast);//add the toast to the arrayList
        }
    }
    
    public void testToasts()
    {
        //test that the toasts created has the same context and content as the ones passed in
        for (int i =0 ; i < toastAmunts ; i ++)
        {
            String toastContent = ((TextView)((LinearLayout)toasty.get(i).getView()).getChildAt(0)); //i saw at https://stackoverflow.com/questions/9002706/how-to-get-a-text-from-a-toast-object that this is hhow to get the content of the toast
            Context c ; // get the context of the Toast 
            
            if ( toastContent !=contents[i] || c != contexts[i])
            {
                //test for this unit has failed
                System.out.println("Unit test for toast content: "+contents[i]+"  and context: "+ contexts[i]+" has failed");
            }
        }
    }
    
}
