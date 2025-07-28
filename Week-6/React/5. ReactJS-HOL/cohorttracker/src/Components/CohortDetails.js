// src/CohortDetails.js
import CohortDetails from './components/CohortDetails';

import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails() {
  const cohort = {
    name: 'Cognizant React Training',
    duration: '6 Weeks',
    trainer: 'John Doe',
  };

  return (
    <div className={styles.card}>
      <h2 style={{ color: 'darkblue' }}>Cohort Details</h2>
      <p><strong>Name:</strong> {cohort.name}</p>
      <p><strong>Duration:</strong> {cohort.duration}</p>
      <p><strong>Trainer:</strong> {cohort.trainer}</p>
    </div>
  );
}

export default CohortDetails;
