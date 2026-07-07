fun <I, O> memoize(f: (I) -> O): (I) -> O {
        val cache = mutableMapOf<I, O>()
        return { input -> cache.getOrPut(input) { f(input) } }
}
// square grid traversal with step cost = 1 
fun gridStep(n: Int, pos: Array<Int>, gate: (Int,Int)->Boolean, q: ArrayDeque<Pair<Int,Int>>, src: Pair<Int,Int>, dest: Pair<Int,Int>) {
  val (x,y) = dest
  val (sx,sy) = src
  if(x>= 0 && x < n && y >=0 && y < n && gate(x,y) && pos[y*n+x] == 0){
    pos[y*n+x] = pos[sy*n+sx] + 1
    q.addLast(dest)
  }
}
    
fun gridTraversal(n: Int, pos: Array<Int>, gate: (Int,Int)->Boolean, exit: (Int,Int) -> Boolean, q: ArrayDeque<Pair<Int,Int>>): Boolean {
  while(ongoing && q.isNotEmpty()){
    val (x,y) = q.removeFirst()
    if(exit(x,y)){
      return true}
    else {
      gridStep(n,pos,gate,q,Pair(x,y), Pair(x-1,y))
      gridStep(n,pos,gate,q,Pair(x,y), Pair(x+1,y))
      gridStep(n,pos,gate,q,Pair(x,y), Pair(x,y-1))
      gridStep(n,pos,gate,q,Pair(x,y), Pair(x,y+1))
    }
  }
  return false
}
// Entry: low always true, high always false   
fun binSearch(low: Int, high: Int, pred: (Int) -> Boolean): Int {
            if(high - low < 2){
                return low
            } else{
                val m = (low+high)/2
                if(pred(m)){
                    return binSearch(m,high,pred)
                }else{
                    return binSearch(low,m,pred)
                }
            }
        }
