//the read function will only be called once for each test case.
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int numread = 0;
        boolean eof = false;
        while(!eof && numread<n){
            int size = read4(buffer);
            if(size<4) eof =true;
            int bytes = Math.min(n-numread,size);
            System.arraycopy(buffer,0,buf,numread,bytes);
            numread +=bytes;
        }
        return numread;
    }
}
