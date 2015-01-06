(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.8" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.0" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +version+ "2.6.0-2")
(bootlaces! +version+)

(task-options!
  pom  {:project     'cljsjs/moment
        :version     +version+
        :description "A javascript date library for parsing, validating, manipulating, and formatting dates."
        :url         "http://momentjs.com/"
        :license     {:name "MIT" :url "http://opensource.org/licenses/MIT"}
        :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
  (comp
    (download :url "https://github.com/moment/moment/archive/2.6.0.zip"
              :checksum "0f9b226ff824066a2040056a4abfa0f6"
              :unzip true)
    (sift :move {#"^moment-.*/moment.js"         "cljsjs/development/moment.inc.js"
                 #"^moment-.*/min/moment.min.js" "cljsjs/production/moment.min.inc.js"})
    (sift :include #{#"^cljsjs"})))
