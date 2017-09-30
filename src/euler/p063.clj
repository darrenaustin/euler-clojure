(ns euler.p063
  (:use euler.numeric))

;; Find numbers where x^n are n digits long.  x must be a single
;; digit, as any x > 9 will yeild at least an n+1 digit number.
;;
;; The upper bound for n will be when 9^n = 10^(n-1).  This =>
;; n * log(9) = (n-1) * log(10)         =>
;; n * log(9) = n * log(10) - log(10)   =>
;; log(10) = n * log(10) - n * log(9)   =>
;; log(10) = n * (log(10) - log(9))     =>
;; n = ((log(10) - log(9)) / log(10)
;;
;; So with that max power, we can just iterate through the single
;; digits raised to powers less than n and count which ones are
;; n digit numbers
(defn solution {:answer 49} []
  (let [max-power (int (/ (Math/log 10) (- (Math/log 10) (Math/log 9))))]
    (count (for [n (range 0 (inc max-power))
                 x (range 1 10)
                 :let [xn (exp x n)]
                 :when (= (count (str xn)) n)]
             xn))))

