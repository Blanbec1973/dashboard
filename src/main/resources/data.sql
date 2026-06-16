-- Exemple de donnees initiales pour la table monitored_target
-- Adaptez les valeurs (id, path, display_name, etc.) a votre environnement.
DELETE FROM monitored_target;
INSERT INTO monitored_target (id, type, display_name, path, display_order, enabled, created_at, updated_at)
VALUES (
    '550e8400-e29b-41d4-a716-446655440000',
    'WINDOWS_FOLDER',
    'Documents Pro',
    'C:/Users/heynerr/Documents',
    1,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP);
INSERT INTO monitored_target (id, type, display_name, path, display_order, enabled, created_at, updated_at)
VALUES (     '94cfdaf4-0ca7-4b6b-80cd-e8c00de6cc7a',
             'WINDOWS_FOLDER',
             'Téléchargements',
             'C:/Users/heynerr/Downloads',
             2,
             TRUE,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP);


