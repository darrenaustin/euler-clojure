(ns dma.euler.util)

(defn words-from [filename]
  (map #(.replace % "\"" "") (.split (slurp filename) ",")))
