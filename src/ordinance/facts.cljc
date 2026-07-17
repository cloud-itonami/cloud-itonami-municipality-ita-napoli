(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Naples (Comune di Napoli) --
  a Wave 1b addition per ADR-2607171400 addendum 2, joining the
  cloud-itonami-municipality-* compliance-fact family of ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL comune.napoli.it PDF -- never fabricated.
  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/number.

  Both entries below were verified on 2026-07-17 by downloading each
  source PDF from the Comune di Napoli regulations index
  (comune.napoli.it, 'Statuti e regolamenti') and directly reading the
  PDF text via the Read tool: each title, deliberation number and date
  stated here appears on the title page of the cited PDF.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"napoli"
   [{:ordinance/id "napoli.regolamento-toponomastica-numerazione-civica-2021"
     :ordinance/title "Regolamento comunale per la toponomastica e la numerazione civica"
     :ordinance/municipality "napoli"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Deliberazione C.C. n. 42 del 03/10/2012, modificato con Deliberazione C.C. n. 2 del 22/01/2021"
     :ordinance/url "https://static-www.comune.napoli.it/wp-content/uploads/2025/09/regolamento_toponomastica_CC_n_2_del_22_1_2021.pdf"
     :ordinance/url-provenance :official-comune-napoli-it
     :ordinance/enacted-date "2012-10-03"
     :ordinance/last-revised-date "2021-01-22"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:toponymy :civic-numbering}}
    {:ordinance/id "napoli.regolamento-polizia-mortuaria-2006"
     :ordinance/title "Regolamento di polizia mortuaria e dei servizi funebri e cimiteriali"
     :ordinance/municipality "napoli"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Deliberazione C.C. n. 11 del 21/02/2006, art. 10 sostituito con Deliberazione C.C. n. 28 del 16/07/2015"
     :ordinance/url "https://static-www.comune.napoli.it/wp-content/uploads/2025/09/regolamento_cimiteri.pdf"
     :ordinance/url-provenance :official-comune-napoli-it
     :ordinance/enacted-date "2006-02-21"
     :ordinance/last-revised-date "2015-07-16"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-health :cemetery-services}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ita-napoli Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "napoli")) " Napoli entries seeded with "
                 "official comune.napoli.it citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
