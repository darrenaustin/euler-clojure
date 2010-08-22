(ns dma.euler.p060
  (:use [dma.euler numeric [primes :exclude [prime?]]]
        clojure.contrib.math
        clojure.contrib.def))

(def prime-vec (vec (take-while-< 10000 (primes))))
(def prime-set (set prime-vec))

(defn-memo prime? [n]
  (let [max-prime (last prime-vec)]
    (if (<= n max-prime)
      (not (nil? (prime-set n)))
      (let [max-factor (ceil (sqrt n))]
        (loop [ps prime-vec]
          (if-let [p (first ps)]
            (cond (div? n p) false
                  (> p max-factor) true
                  :else (recur (rest ps)))
            (every? identity (for [p (range max-prime max-factor 2)]
                               (not (div? n p))))))))))

(defn concat-prime? [a b]
  (and (prime? (Integer. (str a b)))
       (prime? (Integer. (str b a)))))

(defn concat-primes? [n candidates]
  (every? #(concat-prime? n %) candidates))

(defn search [matches candidates target-size]
  (if (= (count matches) target-size)
    matches
    (when-let [c (first candidates)]
      (if (concat-primes? c matches)
        (or (search (conj matches c) (rest candidates) target-size)
            (search matches (rest candidates) target-size))
        (recur matches (rest candidates) target-size)))))

(defn solution {:answer 26033} []
  (sum (search [] (rest prime-vec) 5)))
