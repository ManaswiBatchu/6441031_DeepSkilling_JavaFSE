import React from 'react';
import CohortDetails from './CohortDetails';

function App() {
  const cohorts = [
    {
      cohortName: 'INTADMDF10 - .NET FSD',
      startDate: '22-Feb-2022',
      status: 'Scheduled',
      coach: 'Aathma',
      trainer: 'Jojo Jose',
    },
    {
      cohortName: 'ADM21JF014 - Java FSD',
      startDate: '10-Sep-2021',
      status: 'Ongoing',
      coach: 'Apoorv',
      trainer: 'Elisa Smith',
    },
    {
      cohortName: 'CDBJF21025 - Java FSD',
      startDate: '24-Dec-2021',
      status: 'Ongoing',
      coach: 'Aathma',
      trainer: 'John Doe',
    },
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h2>Cohorts Details</h2>
      {cohorts.map((c, index) => (
        <CohortDetails
          key={index}
          cohortName={c.cohortName}
          startDate={c.startDate}
          status={c.status}
          coach={c.coach}
          trainer={c.trainer}
        />
      ))}
    </div>
  );
}

export default App;