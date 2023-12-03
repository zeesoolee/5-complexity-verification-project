package snippet_splitter_out.ds_6;
public class ds_6_snip_3$HibernateORM_execute {
// org.hibernate.hql.spi.id.TableBasedUpdateHandlerImpl.execute(org.hibernate.engine.spi.SharedSessionContractImplementor,org.hibernate.engine.spi.QueryParameters)
// @Override // Removed to allow compilation
// SNIPPET_STARTS
public int execute(SharedSessionContractImplementor session, QueryParameters queryParameters) throws Exception {
    // throws Exception added to allow compilation
    prepareForUse(targetedPersister, session);
    try {
        // First, save off the pertinent ids, as the return value
        PreparedStatement ps = null;
        int resultCount = 0;
        try {
            try {
                ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement(idInsertSelect, false);
                int position = 1;
                position += handlePrependedParametersOnIdSelection(ps, session, position);
                for (ParameterSpecification parameterSpecification : idSelectParameterSpecifications) {
                    position += parameterSpecification.bind(ps, queryParameters, session, position);
                }
                resultCount = session.getJdbcCoordinator().getResultSetReturn().executeUpdate(ps);
            } finally {
                if (ps != null) {
                    session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release(ps);
                    session.getJdbcCoordinator().afterStatementExecution();
                }
            }
        } catch (SQLException e) {
            throw session.getJdbcServices().getSqlExceptionHelper().convert(e, "could not insert/select ids for bulk update", idInsertSelect);
        }
        // Start performing the updates
        for (int i = 0; i < updates.length; i++) {
            if (updates[i] == null) {
                continue;
            }
            try {
                try {
                    ps = session.getJdbcCoordinator().getStatementPreparer().prepareStatement(updates[i], false);
                    if (assignmentParameterSpecifications[i] != null) {
                        // jdbc params are 1-based
                        int position = 1;
                        for (int x = 0; x < assignmentParameterSpecifications[i].length; x++) {
                            position += assignmentParameterSpecifications[i][x].bind(ps, queryParameters, session, position);
                        }
                        handleAddedParametersOnUpdate(ps, session, position);
                    }
                    session.getJdbcCoordinator().getResultSetReturn().executeUpdate(ps);
                } finally {
                    if (ps != null) {
                        session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release(ps);
                        session.getJdbcCoordinator().afterStatementExecution();
                    }
                }
            } catch (SQLException e) {
                throw session.getJdbcServices().getSqlExceptionHelper().convert(e, "error performing bulk update", updates[i]);
            }
        }
        return resultCount;
    } finally {
        releaseFromUse(targetedPersister, session);
    }
}
}