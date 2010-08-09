(ns dma.euler.util)

(defn words-from [filename]
  (map #(.replace % "\"" "") (.split (slurp filename) ",")))

(defn collect [cols]
  (mapcat identity cols))

(defn indexed-map
  ([keys] (indexed-map keys 0))
  ([keys start-value]
     (apply hash-map (interleave keys (iterate inc start-value)))))

(defn compare-seqs [s1 s2]
  (if (empty? s1)
    (compare nil (first s2))
    (let [comparison (compare (first s1) (first s2))]
      (if (zero? comparison)
        (compare-seqs (rest s1) (rest s2))
        comparison))))
