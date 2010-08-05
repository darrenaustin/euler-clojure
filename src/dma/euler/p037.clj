(ns dma.euler.p037
  (:use [dma.euler numeric primes]))

(defn truncations [n]
  (let [ds (vec (digits n)), num-ds (count ds)]
    (concat
     (for [i (range 1 num-ds)] (number (subvec ds i)))
     (for [i (range 1 num-ds)] (number (subvec ds 0 (- num-ds i)))))))

(defn prime-truncations? [n primes]
  (every? primes (truncations n)))

(defn solution {:answer 748317} []
   (let [ps (apply sorted-set (take-while-< 1000000 (primes)))]
     (sum (take 11 (filter #(prime-truncations? % ps) (drop-while-< 10 ps))))))

;; The following was my first solution that didn't depend on the upper prime bound
;; of 1 mil.  It solved it in time, but was much slower (~45secs)
;;
;; (defn prime-truncations? [n primes]
;;   (every? #(prime? % primes) (truncations n)))
;;
;; (defn solution {:answer 748317} []
;;   (let [ps (primes)]
;;     (sum (take 11 (filter #(prime-truncations? % ps) (drop-while-< 10 ps))))))

