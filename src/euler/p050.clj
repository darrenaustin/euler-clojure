(ns euler.p050
  (:use [euler numeric primes]))

(defn solution {:answer 997651} []
  (let [primes (vec (take-while-< 1000000 (primes)))
        prime? (into #{} primes)
        last-prime (last primes)
        num-primes (count primes)]
    (loop [start 0, max-prime 0, max-size 0]
      (if (>= start (- num-primes max-size))
        max-prime
        (let [[max-prime max-size]
              (loop [x start, sum 0, size 0, max-prime max-prime, max-size max-size]
                (if (or (>= start num-primes)
                        (> sum last-prime))
                  [max-prime max-size]
                  (let [sum (+ sum (nth primes x)), size (inc size)]
                    (if (and (prime? sum) (> size max-size))
                      (recur (inc x) sum size sum size)
                      (recur (inc x) sum size max-prime max-size)))))]
          (recur (inc start) max-prime max-size))))))
