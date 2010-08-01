(ns dma.euler.primes
  (:use dma.euler.numeric))

(defn- cand-index [n] (dec (quot n 2)))

(defn- make-candidates [max]
  (apply vector (replicate (cand-index max) true)))

(defn- find-next [start candidates]
  (let [where (cand-index start)]
    (cond (>= where (count candidates)) nil
          (nth candidates where) start
          :else (recur (+ start 2) candidates))))

(defn- mark-multiples [n candidates]
  (loop [m (cand-index (* n n))
         c candidates
         end (count candidates)]
    (if (>= m end)
      c
      (recur (+ m n) (assoc c m false) end))))

(defn- sieve [start candidates]
  (if-let [next (find-next start candidates)]
    (lazy-seq
     (cons next (sieve (+ 2 next) (mark-multiples next candidates))))))

(defn prime-sieve [max]
  (cons 2 (sieve 3 (make-candidates (int max)))))

(defn- times-divisible
  ([n div] (times-divisible n div 0))
  ([n div times]
     (if (div? n div)
       (recur (/ n div) div (inc times))
       [times n])))

(defn prime-factorization [n & primes]
  (loop [factors []
         value n
         ps (or primes (prime-sieve (inc (Math/sqrt n))))]
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
