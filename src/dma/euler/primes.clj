(ns dma.euler.primes
  (:use dma.euler.numeric))

;; Returns a lazy sequence of primes
;; Shamefully lifted from Christophe Grand's site:
;; http://clj-me.cgrand.net/2009/07/30/everybody-loves-the-sieve-of-eratosthenes/
(defn primes []
  (letfn [(enqueue [sieve n step]
                   (let [m (+ n step)]
                     (if (sieve m)
                       (recur sieve m step)
                       (assoc sieve m step))))
          (next-sieve [sieve candidate]
                      (if-let [step (sieve candidate)]
                        (-> sieve
                            (dissoc candidate)
                            (enqueue candidate step))
                        (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
                       (if (sieve candidate)
                         (recur (next-sieve sieve candidate) (+ candidate 2))
                         (cons candidate
                               (lazy-seq (next-primes (next-sieve sieve candidate)
                                                      (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(defn- simple-brute-force-prime? [n]
  (cond (< n 2) false
        (< n 4) true
        :else (every? identity (for [x (range 3 (inc (Math/sqrt n)) 2)]
                                 (not (div? n x))))))

(defn prime? [n]
  (and (.isProbablePrime (BigInteger/valueOf n) 1)
       (simple-brute-force-prime? n)))

(defn- times-divisible
  ([n div] (times-divisible n div 0))
  ([n div times]
     (if (div? n div)
       (recur (/ n div) div (inc times))
       [times n])))

(defn prime-factorization [n & ps]
  (loop [factors []
         value n
         ps (or (first ps) (primes))]
    (if (or (empty? ps) (> (square (first ps)) value))
      (if (> value 1)
        (conj factors [value 1])
        factors)
      (let [[times rem] (times-divisible value (first ps))]
        (if (zero? times)
          (recur factors value (rest ps))
          (recur (conj factors [(first ps) times]) rem (rest ps)))))))

(defn flat-prime-factorization [n]
  (mapcat #(replicate (second %) (first %)) (prime-factorization n)))

(defn prime-factors [n]
  (map first (prime-factorization n)))
