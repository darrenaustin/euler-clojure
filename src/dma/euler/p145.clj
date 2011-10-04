(ns dma.euler.p145)

;;
;; Compute by analysis
;;
(defn num-digit-couple-sums [f carry? start]
  (count (for [a (range start 10) b (range start 10)
               :let [s (+ a b)]
               :when (and (f s) (if carry? (> s 9) (< s 10)))]
           [a b])))

(defn solution {:answer 608720} []
  (let [even-no-carry (num-digit-couple-sums even? false 0)
        even-carry (num-digit-couple-sums even? true 0)
        odd-no-carry (num-digit-couple-sums odd? false 0)
        odd-no-carry-no-zero (num-digit-couple-sums odd? false 1)
        odd-carry (num-digit-couple-sums odd? true 0)]
    (+
     ;; 1 digit (a + a)
     ;; 
     ;; There are none, as 2 * a will always be even
     0

     ;; 2 digit (ab + ba)
     ;;
     ;; a+b Must be odd with no zeros.  If it had a carry, it would
     ;; cause the first digit to go even, so:
     odd-no-carry-no-zero

     ;; 3 digit (abc + cba)
     ;; 
     ;; a+c must be odd with no zeros.  b+b is always even, so we need
     ;; a+c to carry.  But b+b+1 must not carry, so b => [0 1 2 3 4],
     ;; or 5 choices for b
     (* 5 odd-no-carry-no-zero)

     ;; 4 digit (abcd + dcba)
     ;;
     ;; a+d must be odd with no zeros.  If b+c has a carry then one of
     ;; b+c or c+b will be even and the other will be odd.  Therefore
     ;; b+c can't carry.  If a+d carries, then c+b must be even but
     ;; that would mean b+c is even as well (unless c+b+1 carries, which
     ;; can't happen if c+b is even).  Therefore, a+d doesn't carry:
     (* odd-no-carry-no-zero odd-no-carry)

     ;; 5 digit (abcde + edcba)
     ;;
     ;; a+e must be odd with no zeros. c+c is always even. In order
     ;; to make it odd b+d must carry.  But if it does then the first a+e will
     ;; even.  Therefore there are no 5 digit reversible numbers
     0
     
     ;; 6 digit (abcdef + fedcba)
     ;;
     ;; a+f must be odd with no zeros. b+e must not carry (or it will
     ;; flip a+f) c+d must not carry (or it will flip c+d). Therefore
     ;; a+f must not carry either, or it would flip e+b but not b+e.
     ;; So everything must be odd, but not carry.
     (* odd-no-carry-no-zero odd-no-carry odd-no-carry)

     ;; 7 digit (abcdefg + gfedcba)
     ;; 
     ;; a+g must be odd with no zeros. b+f must not carry. d+d is
     ;; even, so c+e must carry (and be odd).  If d+d carries, then
     ;; one of c+e will flip.  Therefore d+d must not carry so d => [0
     ;; 1 2 3 4], or 5 choices for d.  Because c+e carries, one of the
     ;; b+f will be flipped, so a+g must also carry to flip the other
     ;; one.  Which means b+f must be even with no carry:
     (* odd-carry even-no-carry odd-carry 5)

     ;; 8 digit (abcdefgh + hgfedcba)
     ;;
     ;; a+h must be odd with no zeros. b+g can not carry. If d+e
     ;; carries, then c+f must be even.  However, because b+g can't
     ;; carry, f+c won't get flipped as well, so d+e can't carry.
     ;; c+f can't carry either, or it will throw off d+e.  This also
     ;; means that a+h can not carry either or it will throw off b+g.
     (* odd-no-carry-no-zero odd-no-carry odd-no-carry odd-no-carry)


     ;; 9 digit (abcdefghi + ihgfedcba)
     ;;
     ;; a+i is odd with no zeros.  b+h can not carry.  e+e is even, so
     ;; d+f must carry. Which means b+h must carry in order to offset the
     ;; d+f carry on c+g.  But that conflicts with b+h not carrying because
     ;; it would flip the first a+i.  Therefore there are no 9-digit
     ;; reversible numbers
     0)))
