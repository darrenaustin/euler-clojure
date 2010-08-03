(ns dma.euler.p032
  (:use dma.euler.numeric clojure.contrib.combinatorics))

(defn solution {:answer 45228} []
  (sum
   (into #{}
         (filter identity
                 (map (fn [ds]
                        (let [a (number (subvec ds 0 2))
                              b (number (subvec ds 2 5))
                              c (number (subvec ds 0 1))
                              d (number (subvec ds 1 5))
                              p (number (subvec ds 5))]
                          (if (or (= (* a b) p)
                                  (= (* c d) p))
                            p)))
                     (lex-permutations (range 1 10)))))))

